import SwiftUI
import SwiftDevPackage

struct TabTitleWithBack: View {
    let title: String
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    var body: some View {
        ZStack {
            HStack {
                Image(systemName: "chevron.left")
                    .padding(.leading, 24.0)
                    .foregroundColor(blueLightPrimary)
                    .font(Font.title.weight(.bold))
                    .onTapGesture { navigation.pop() }
                Spacer()
            }
            HStack {
                Spacer()
                TabTitleText(text: title)
                Spacer()
            }
        }        
        .padding(.bottom, 16.0)
        .frame(maxWidth: .infinity)
        .background(tabTiteBackgroundColor)
    }
}
