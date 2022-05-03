import SwiftUI

struct TabTitle: View {
    let title: String
    
    var body: some View {
        HStack {
            TabTitleText(text: title)
                .padding(.leading, topTitleLeadingPadding)
            Spacer()
        }        
        .padding(.bottom, topTitleBottomPadding)
        .frame(maxWidth: .infinity)
        .background(tabTiteBackgroundColor)
    }
}
