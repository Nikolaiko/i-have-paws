import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        UITableView.appearance().separatorStyle = .none
        UITableViewCell.appearance().backgroundColor = transparentUIColor
        UITableView.appearance().backgroundColor = transparentUIColor
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
