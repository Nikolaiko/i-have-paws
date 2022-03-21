import SwiftUI

struct TabTitleText: View {
    let text: String
    
    var body: some View {
        Text(text)
            .font(tabTitleFont)
            .foregroundColor(tabTitleTextColor)
    }
}

struct TabTitleText_Previews: PreviewProvider {
    static var previews: some View {
        TabTitleText(text: "Groups")
    }
}
