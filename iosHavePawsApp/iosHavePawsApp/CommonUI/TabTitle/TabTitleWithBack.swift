import SwiftUI
import SwiftDevPackage

struct TabTitleWithBack: View {
    let title: String
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    var body: some View {
        HStack {
            Image(systemName: "chevron.left")
                .foregroundColor(Color.black)
                .font(Font.title.weight(.bold))
                .onTapGesture { navigation.pop() }
            
            TabTitleText(text: title)
                .padding(.leading, 12.0)
        }
        .padding(.top, 45.0)
        .padding(.bottom, 16.0)
        .frame(maxWidth: .infinity)
        .background(tabTiteBackgroundColor)
    }
}
