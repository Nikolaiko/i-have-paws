import SwiftUI

struct ApplicationButton: View {
    private let width: CGFloat?
    private let height: CGFloat?
    private let enabled: Bool
    private let backgroundColor: Color

    private let title: String
    private let callback: VoidCallback

    init(
        buttonTitle: String,
        buttonCallback: @escaping VoidCallback = {},
        buttonWidth: CGFloat? = nil,
        buttonHeight: CGFloat? = nil,
        buttonEnabled: Bool = true,
        buttonColor: Color = Color.blue
    ) {
        width = buttonWidth
        height = buttonHeight
        callback = buttonCallback
        title = buttonTitle
        enabled = buttonEnabled
        backgroundColor = buttonColor
    }

    var body: some View {
        Button(action: callback) {
            Text(title)
                .font(bottomButtonFont)
                .padding(6.0)
                .frame(maxWidth: width, maxHeight: height)
                .foregroundColor(bottomButtonTextColor)
                .background(enabled ? backgroundColor : mainDisabledButtonColor)
                .cornerRadius((height ?? 0.0) * 0.24)
        }
        .accessibilityLabel(applicationButtonLabel)
        .disabled(!enabled)
    }
}
