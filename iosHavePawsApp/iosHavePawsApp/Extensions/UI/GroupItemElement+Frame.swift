import Foundation

import Foundation
import SwiftUI

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
