import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    let local = RealmStorage()

	var body: some View {
        let _ = print(local.getAllGroups())
		Text(greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
