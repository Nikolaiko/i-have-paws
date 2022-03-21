import SwiftUI
import SwiftDevPackage

struct ContentView: View {
	var body: some View {
        NavigationControllerView(transition: .custom(.slide, .slide)) {
            GroupsTab()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
