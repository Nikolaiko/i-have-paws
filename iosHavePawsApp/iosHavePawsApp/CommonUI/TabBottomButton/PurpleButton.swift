import SwiftUI

struct PurpleButton: View {
    private let width: CGFloat?
    private let height: CGFloat?
    
    private let title: String
    private let callback: VoidCallback
    
    init(
        buttonTitle: String,
        buttonCallback: @escaping VoidCallback = {},
        buttonWidth: CGFloat? = nil,
        buttonHeight: CGFloat? = nil
    ) {
        width = buttonWidth
        height = buttonHeight
        callback = buttonCallback
        title = buttonTitle
    }
    
    var body: some View {
        Button(action: callback) {
            Text(title)
                .padding(6.0)
                .frame(maxWidth: width, maxHeight: height)                
                .foregroundColor(Color.white)
                .background(mainPurpleColor)
                .cornerRadius((height ?? 0.0) * 0.24)
        }
    }
}

struct TabBottomButton_Previews: PreviewProvider {
    static var previews: some View {
        PurpleButton(buttonTitle: "Title")
    }
}
