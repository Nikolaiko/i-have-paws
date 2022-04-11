import Foundation
import SwiftUI

struct InputTextFieldStyle: TextFieldStyle {
    static let defaultCorderRadius = 0.0
    static let defaultBackgroundColor = Color.white
    static let defaultStrokeColor = Color.black
    static let defaultStrokeWidth = 4.0
    
    private let cornerRadius: CGFloat
    private let backgroundColor: Color
    private let strokeColor: Color
    private let strokeWidth: CGFloat
    
    init(
        radius: CGFloat = InputTextFieldStyle.defaultCorderRadius,
        background: Color = InputTextFieldStyle.defaultBackgroundColor,
        strokeLineColor: Color = InputTextFieldStyle.defaultStrokeColor,
        strokeLineWidth: CGFloat = InputTextFieldStyle.defaultStrokeWidth
    ) {
        cornerRadius = radius
        backgroundColor = background
        strokeColor = strokeLineColor
        strokeWidth = strokeLineWidth
    }
    
    func _body(configuration: TextField<_Label>) -> some View {
        configuration
            .background(
                RoundedRectangle(cornerRadius: cornerRadius)
                    .stroke(strokeColor, lineWidth: strokeWidth)
                    .background(backgroundColor)
            )
    }
}
