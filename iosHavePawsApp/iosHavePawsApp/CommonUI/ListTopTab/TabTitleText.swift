import SwiftUI

struct TabTitleText: View {
    let text: String

    var body: some View {
        Text(text)
            .lineLimit(1)
            .font(tabTitleFont)
            .foregroundColor(tabTitleTextColor)
            .accessibilityLabel(tabTitleTextLabel)
    }
}
