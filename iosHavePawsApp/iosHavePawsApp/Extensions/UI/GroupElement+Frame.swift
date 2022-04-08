import Foundation
import SwiftUI

extension GroupElement {
    func computeFrame(frameSize: CGSize) -> some View {
        return frame(
            width: frameSize.width * groupItemWidthCoff,
            height: frameSize.height * groupItemHeightCoff
        )
        .background(blueLight20)
        .cornerRadius(frameSize.height * groupItemCornerCoff)
        .padding(.vertical, (frameSize.height * groupItemVerticalSpacingCoff) / 2.0)
    }
}
