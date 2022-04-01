import Foundation
import Resolver
import shared

class GroupsScreenPresenter: ObservableObject {
    @Injected private var reducer: GroupsScreenReducer
    @Injected private var navigation: AppNavigation
    
    private lazy var stateHandler: FlowObserver = {
        let handler = FlowObserver(callback: stateUpdate)
        return handler
    }()
    
    private lazy var messageHandler: FlowObserver = {
        let handler = FlowObserver(callback: messageReceived)
        return handler
    }()
    
    @Published var groupsList: [Group] = []
    
    init() {
        reducer.state.collect(collector: stateHandler) { kotlinUnit, error in
            print("Complete states?")
        }
        
        reducer.messages.collect(collector: messageHandler) { kotlinUnit, error in
            print("Complete messages?")
        }
    }
    
    func refreshGroupsList() {
        reducer.refreshGroupsList()
    }
    
    func openGroupScreen(item: shared.Group) {
        navigation.navigateTo(destination: .groupScreen, parameter: item)
    }
    
    private func stateUpdate(newState: Any?) {
        if let state = newState as? GroupsScreen.State {
            groupsList = state.groups
        }
    }
    
    private func messageReceived(newMessage: Any?) {
        if let message = newMessage as? StateMessage {
            print("Message! : \(message.text)")
        }
    }
}
