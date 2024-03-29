import Foundation
import Resolver
import shared
import SwiftDevPackage

extension Resolver: ResolverRegistering {

    public static func registerAllServices() {
        // Navigation
        register { NavigationControllerViewModel(easing: .easeOut(duration: 0.33)) }
            .scope(.application)

        register { Router(navigation: resolve()) as AppNavigation }
            .scope(.application)

        // Local Storage
        //register { DatabaseDriverFactory() as DatabaseDriverFactory }
        register { StubStorage() as LocalStorage  }
            .scope(.application)

        // Reducers
        register { GroupsScreenReducer_(storage: resolve()) as GroupsScreenReducer }
            .scope(.shared)

        register { AddGroupReducer_(storage: resolve()) as AddGroupReducer }
            .scope(.shared)

        register { AddGroupItemReducer_(storage: resolve()) as AddGroupItemReducer }
            .scope(.shared)

        register { GroupScreenReducer_(storage: resolve()) as GroupScreenReducer }
            .scope(.shared)
    }
}
