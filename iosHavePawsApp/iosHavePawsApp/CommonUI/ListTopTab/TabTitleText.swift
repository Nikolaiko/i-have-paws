import SwiftUI

struct TabTitleText: View {
    let text: String

    var body: some View {
        Text(text)
            .font(tabTitleFont)
            .foregroundColor(tabTitleTextColor)
    }
}
