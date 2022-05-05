import SwiftDevPackage
import SwiftUI
import shared

class Router: AppNavigation {
    private let navigationStack: NavigationControllerViewModel

    init(navigation: NavigationControllerViewModel) {
        navigationStack = navigation
    }

    func navigateTo(destination: NavigationDestination, parameter: Any? = nil) {
        DispatchQueue.main.async {
            switch destination {
            case .popBackStack:
                self.navigationStack.pop()
            case .groupScreen:
                self.navigateToGroupScreen(parameter: parameter)
            }
        }
    }

    private func navigateToGroupScreen(parameter: Any? = nil) {
        if let group = parameter as? shared.Group {
            self.navigationStack.push(GroupView(viewGroup: group))
        }
    }
}
