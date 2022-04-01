import SwiftUI
import shared
import Resolver

class GroupScreenPresenter: ObservableObject {
    @Published var group: shared.Group? = nil
    @Published var showAddItemMenu: Bool = false
    @Published var newItemName: String = ""
    
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
        let item = shared.GroupItem(id: UUID().uuidString, title: itemName ?? "")
        reducer.addGroupItem(item: item)
    }
    
    private func stateUpdate(newState: Any?) {
        if let state = newState as? GroupScreen.State {
            group = state.group
            print(group)
        }
    }
    
    private func messageReceived(newMessage: Any?) {
        if let message = newMessage as? StateMessage {
            print("Message! : \(message.text)")
        }
    }
}
