import Foundation
import SwiftUI

struct RoundedShapeModifier: ViewModifier {
    let width: CGFloat
    let height: CGFloat
    let roundedCornerCoff: CGFloat
    
    func body(content: Content) -> some View {        
        content
            .frame(maxWidth: width, maxHeight: height)
            .cornerRadius(height / roundedCornerCoff)
            .background(mainPurpleColor)
    }
}
