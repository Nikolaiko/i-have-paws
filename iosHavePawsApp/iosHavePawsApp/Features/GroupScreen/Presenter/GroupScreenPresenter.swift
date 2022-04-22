import SwiftUI
import shared
import Resolver
import Combine

class GroupScreenPresenter: ObservableObject {
    @Published var group: shared.Group? = nil
    @Published var enableRandomButton = false
    @Published var showErrorMessage = false
    @Published var showRandomResult = false
    
    var errorMessage = ""
    var selectedItemName = ""
    
    @Injected private var reducer: GroupScreenReducer
    
    private var subscriptions: Set<AnyCancellable> = []
    
    init() {
        FlowPublisher(kotlinFlow: reducer.state).sink { [weak self] state in
            self?.stateUpdate(newState: state)
        }.store(in: &subscriptions)
        
        FlowPublisher(kotlinFlow: reducer.messages).sink { [weak self] message in
            self?.messageReceived(newMessage: message)
        }.store(in: &subscriptions)
    }
    
    func initWithGroup(item: shared.Group) {
        group = item
        refreshGroup()
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
    
    func refreshGroup() {
        if let selectedGroup = group {
            reducer.getGroup(group: selectedGroup)
        }
    }
    
    private func stateUpdate(newState: GroupScreen.State) {
        group = newState.group
        enableRandomButton = newState.randomEnabled
    }
    
    private func messageReceived(newMessage: StateMessage) {
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
