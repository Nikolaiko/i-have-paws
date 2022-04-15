import Foundation
import SwiftUI

extension GroupElement {
    func computeFrame(frameSize: CGSize) -> some View {
        return frame(
            width: frameSize.width * groupRowWidthCoff,
            height: frameSize.height * groupRowHeightCoff
        )
        .background(blueLight30)
        .cornerRadius(frameSize.height * groupRowCornerCoff)
        .padding(.vertical, (frameSize.height * groupRowVerticalSpacingCoff) / 2.0)
    }
}
