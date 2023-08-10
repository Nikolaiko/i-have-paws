import SwiftUI
import shared

// swiftlint:disable type_name
@main
struct iOSApp: App {

    init() {

        UITableView.appearance().separatorStyle = .none
        UITableViewCell.appearance().backgroundColor = transparentUIColor
        UITableView.appearance().backgroundColor = transparentUIColor
    }

	var body: some Scene {
		WindowGroup {
			//ContentView()
            ComposeView()
		}
	}
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
// swiftlint:endable type_name
