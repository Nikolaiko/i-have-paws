import SwiftUI
import shared
import Resolver

class GroupScreenPresenter: ObservableObject {
    @Published var group: shared.Group? = nil
    @Published var enableRandomButton = false
    @Published var showErrorMessage = false
    @Published var showRandomResult = false
    
    var errorMessage = ""
    var selectedItemName = ""
    
    @Injected private var reducer: GroupScreenReducer
    
    private lazy var stateHandler: FlowObserver = {
        let handler = FlowObserver(callback: stateUpdate)
        return handler
    }()
    
    private lazy var messageHandler: FlowObserver = {
        let handler = FlowObserver(callback: messageReceived)
        return handler
    }()
    
    init() {
        reducer.state.collect(collector: stateHandler) { kotlinUnit, error in
            print("Complete states?")
        }
        
        reducer.messages.collect(collector: messageHandler) { kotlinUnit, error in
            print("Complete messages?")
        }
    }
    
    func initWithGroup(item: shared.Group) {
        group = item
        reducer.getGroup(group: item)
    }
    
    func tryAddNewGroupItem(itemName: String?) {
        let item = shared.GroupItem(id: UUID().uuidString, title: itemName ?? "", active: true)
        reducer.addGroupItem(item: item)
    }
    
    func updateGroupItemState(item: shared.GroupItem) {
        reducer.toggleGroupItemActiveState(groupItem: item)
    }
    
    func deleteGroupItem(item: shared.GroupItem) {
        reducer.deleteGroupItem(item: item)        
    }
    
    func selectRandomItem() {
        reducer.selectRandomElement()
    }
    
    func hideRandomItem() {
        showRandomResult = false
    }
    
    private func stateUpdate(newState: Any?) {
        if let state = newState as? GroupScreen.State {
            group = state.group
            enableRandomButton = !(group?.items.isEmpty ?? true)
        }
    }
    
    private func messageReceived(newMessage: Any?) {
        guard newMessage != nil else { return }
        
        if let message = newMessage as? StateMessage.SelectedItemMessage {
            selectedItemName = message.selectedItem.title
            showRandomResult = true
        }
        
        if let message = newMessage as? StateMessage.ErrorMessage {
            if !showErrorMessage {
                errorMessage = message.text
                showErrorMessage = true
            }
        }
    }
}
