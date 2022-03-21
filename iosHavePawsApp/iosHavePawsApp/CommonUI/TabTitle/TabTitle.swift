import SwiftUI

struct TabTitle: View {
    let title: String
    
    var body: some View {
        HStack {
            TabTitleText(text: title)
                .padding(.leading, 12.0)
            Spacer()
        }
        .padding(.top, 45.0)
        .padding(.bottom, 16.0)
        .frame(maxWidth: .infinity)
        .background(tabTiteBackgroundColor)
    }
}

struct TabTitle_Previews: PreviewProvider {
    static var previews: some View {
        TabTitle(title: "Groups")
    }
}
