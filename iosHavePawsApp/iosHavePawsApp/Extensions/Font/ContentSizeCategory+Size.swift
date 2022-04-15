import SwiftUI
import Foundation

extension ContentSizeCategory {
    var size: CGFloat {
        switch self {
        case .small:
            return 13
        case .medium:
            return 17
        case .large:
            return 24
        default:
            return 17
        }
    }
}
