import Foundation
import SwiftUI

extension Text {
    func customFont(_ font: Montserrat, _ sizeCategory: ContentSizeCategory, color: Color = .black) -> some View {
        return self.font(.custom(font.rawValue, size: sizeCategory.size))
            .foregroundColor(color)
    }
}
