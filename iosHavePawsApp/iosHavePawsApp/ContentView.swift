import SwiftUI
import SwiftDevPackage
import Resolver

struct ContentView: View {

    var body: some View {
        NavigationControllerView(
            transition: .custom(.slide, .slide),
            viewModel: Resolver.resolve()
        ) {
            GroupsTab()
        }
	}
}
