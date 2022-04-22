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
        FlowPublisher(kotlinFlow: reducer.state).sink { [weak self] newState in
            self?.groupsList = newState.groups
        }.store(in: &subscriptions)
        
        FlowPublisher(kotlinFlow: reducer.messages).sink { [weak self] newMessage in
            print("Message! : \(newMessage.text)")
        }.store(in: &subscriptions)
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
