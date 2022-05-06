import SwiftUI
import SwiftDevPackage

struct TabTitleWithBack: View {
    let title: String
    @EnvironmentObject private var navigation: NavigationControllerViewModel

    var body: some View {
        HStack {
            Image(systemName: "chevron.left")
                .foregroundColor(blueLightPrimary)
                .font(Font.title.weight(.bold))
                .onTapGesture { navigation.pop() }
            TabTitleText(text: title)
            Spacer()
        }
        .padding(.horizontal, 24.0)
        .padding(.bottom, 16.0)
        .frame(maxWidth: .infinity)
        .background(tabTiteBackgroundColor)
    }
}
