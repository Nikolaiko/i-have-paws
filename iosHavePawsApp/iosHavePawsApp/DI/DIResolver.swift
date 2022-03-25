import Foundation
import Resolver
import shared

extension Resolver: ResolverRegistering {
    
    public static func registerAllServices() {
        register { DatabaseDriverFactory() as DatabaseDriverFactory }
        register { SQLDelightStorage(factory: resolve()) as LocalStorage  }
        register { GroupsScreenReducer_(storage: resolve()) as GroupsScreenReducer }
        register { AddGroupReducer_(storage: resolve()) as AddGroupReducer }
    }
}
