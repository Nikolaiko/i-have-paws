import Foundation
import SwiftUI
import Combine
import shared
import Resolver

class AddGroupPresenter: ObservableObject {
    
    @Published var newEntityName = ""
    @Published var addButtonEnabled = false
    
    @Injected private var reducer: AddGroupReducer
    
    private var subscriptions: Set<AnyCancellable> = []
    private var addCallback: ErrorCallback? = nil
    
    init() {
        $newEntityName
            .sink { [weak self] currentValue in
                self?.addButtonEnabled = self?.validateGroupName(value: currentValue) ?? false
            }
            .store(in: &subscriptions)
        
        FlowPublisher(kotlinFlow: reducer.messages).sink { [weak self] message in
            self?.messageReceived(message: message)
        }.store(in: &subscriptions)
    }
    
    func addEntity(callback: ErrorCallback? = nil) {
        addCallback = callback
        reducer.addNewGroup(id: UUID.init().uuidString, name: newEntityName)
    }
    
    private func validateGroupName(value: String) -> Bool {
        value.count >= 4
    }
    
    private func messageReceived(message: StateMessage) {
            switch message {
            case is StateMessage.SuccessMessage:
                addCallback?(nil)
            case is StateMessage.ErrorMessage:
                addCallback?(AppError.addEntityError(message.text))
            case is StateMessage.InfoMessage:
                print("Message! : \(message.text)")
            default:
                print("Unknown message")
        }
    }
}
