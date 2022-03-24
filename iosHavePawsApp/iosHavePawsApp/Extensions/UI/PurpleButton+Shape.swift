import Foundation
import SwiftUI

extension PurpleButton {
    func roundedShape(
        width: CGFloat = .infinity,
        height: CGFloat = .infinity,
        cornerCoff: CGFloat = 0.0
    ) -> some View {
        modifier(RoundedShapeModifier(width: width, height: height, roundedCornerCoff: cornerCoff))
    }
}
