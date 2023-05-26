import Foundation
import Resolver
import shared
import Combine

class GroupsScreenPresenter: ObservableObject {
    @Injected private var reducer: GroupsScreenReducer
    @Injected private var navigation: AppNavigation

    @Published var groupsList: [Group] = []

    private var subscriptions: Set<AnyCancellable> = []

    init() {
        reducer.callback = { [weak self] state in
            self?.groupsList = state.groups
        }

        reducer.messagesCallback = { newMessage in
            print("Message! : \(newMessage.text)")
        }
    }

    func refreshGroupsList() {
        reducer.refreshGroupsList()
    }

    func openGroupScreen(item: shared.Group) {
        navigation.navigateTo(destination: .groupScreen, parameter: item)
    }

    func deleteGroup(item: shared.Group) {
        reducer.removeGroup(groupName: item.name)
    }
}
