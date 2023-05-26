import Foundation
import SwiftUI
import Combine
import shared
import Resolver

class AddGroupItemPresenter: ObservableObject {

    @Published var newEntityName = ""
    @Published var addButtonEnabled = false

    @Injected private var reducer: AddGroupItemReducer

    private var subscriptions: Set<AnyCancellable> = []
    private var addCallback: ErrorCallback?

    init() {
        $newEntityName
            .sink { [weak self] currentValue in
                self?.addButtonEnabled = self?.validateGroupItemName(value: currentValue) ?? false
            }
            .store(in: &subscriptions)

        reducer.messagesCallback = { [weak self] message in
            self?.messageReceived(message: message)
        }        
    }

    func addEntity(groupId: String, callback: ErrorCallback? = nil) {
        addCallback = callback
        let item = shared.GroupItem(id: UUID().uuidString, title: newEntityName, active: true)
        reducer.addGroupItem(groupId: groupId, item: item)
    }

    private func validateGroupItemName(value: String) -> Bool {
        value.count >= RequrementsConstsKt.minEntityNameLength
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
