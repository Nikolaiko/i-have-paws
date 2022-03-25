import Foundation
import SwiftUI
import Combine
import shared
import Resolver

class AddGroupPresenter: ObservableObject {
    @Published var groupName = ""
    @Published var addButtonEnabled = false
    
    @Injected private var reducer: AddGroupReducer
    private var subscriptions: Set<AnyCancellable> = []
    
    private lazy var messageHandler: FlowObserver = {
        let handler = FlowObserver(callback: messageReceived)
        return handler
    }()
    
    init() {
        $groupName
            .sink { [weak self] currentValue in
                self?.addButtonEnabled = self?.validateGroupName(value: currentValue) ?? false
            }
            .store(in: &subscriptions)
        
        reducer.messages.collect(collector: messageHandler) { kotlinUnit, error in
            print("messages completed")
        }
    }
    
    func addGroup() {
        reducer.addNewGroup(id: UUID.init().uuidString, name: groupName)
    }
    
    private func validateGroupName(value: String) -> Bool {
        value.count > 4
    }
    
    private func messageReceived(newMessage: Any?) {
        if let message = newMessage as? StateMessage {
            print("Message! : \(message.text)")
        }
    }
}
