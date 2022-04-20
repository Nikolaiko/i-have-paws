import SwiftUI

struct TabTitle: View {
    let title: String
    
    var body: some View {
        HStack {
            TabTitleText(text: title)
                .padding(.leading, 12.0)
            Spacer()
        }        
        .padding(.bottom, 16.0)
        .frame(maxWidth: .infinity)
        .background(tabTiteBackgroundColor)
    }
}
