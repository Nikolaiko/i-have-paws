import SwiftUI

struct FAB<ButtonView: View>: ViewModifier {
    let color: Color
    let buttonContent: ButtonView
    let action: VoidCallback
    
    func body(content: Content) -> some View {
        GeometryReader { geo in
            ZStack {
                Color.clear
                content
                button(geo)
            }
        }
    }
    
    @ViewBuilder private func button(_ geom: GeometryProxy) -> some View {
        let buttonSize = geom.size.height * fabButtonHeightCoff
        VStack {
            Spacer()
            HStack {
                Spacer()
                buttonContent
                    .frame(width: buttonSize, height: buttonSize)
                    .imageScale(.large)
                    .background(Circle().fill(color))
                    .foregroundColor(Color.white)
                    .onTapGesture(perform: action)
                    .padding(.bottom, geom.size.height * fabBottomPaddingCoff)
                    .padding(.trailing, geom.size.height * fabBottomPaddingCoff)
            }
        }
    }
}
