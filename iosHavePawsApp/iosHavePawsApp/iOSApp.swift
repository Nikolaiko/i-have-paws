import SwiftUI
import shared

// swiftlint:disable type_name
@main
struct iOSApp: App {

    init() {
        MainDIKt.doInitKoin()


        UITableView.appearance().separatorStyle = .none
        UITableViewCell.appearance().backgroundColor = transparentUIColor
        UITableView.appearance().backgroundColor = transparentUIColor
    }

	var body: some Scene {
		WindowGroup {
			InitialView()
		}
	}
}
// swiftlint:endable type_name
