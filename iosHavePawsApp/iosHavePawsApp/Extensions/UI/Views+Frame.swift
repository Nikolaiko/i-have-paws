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

extension GroupItemElement {
    func computeFrame(frameSize: CGSize) -> some View {
        return frame(
            width: frameSize.width * groupItemRowWidthCoff,
            height: frameSize.height * groupItemRowHeightCoff
        )
        .background(Color.white)
        .padding(.vertical, (frameSize.height * groupItemRowVerticalSpacingCoff) / 2.0)
    }
}

extension TabAddPanel {
    func computeFrame(frameSize: CGSize) -> some View {
        _ = print(frameSize.height * topAddHeight)
        return frame(
            width: frameSize.width,
            height: frameSize.height * topAddHeight
        )
        .background(tabAddBackgroundColor)
        .padding(.top, frameSize.height * topAddTopPadding)
    }
}
