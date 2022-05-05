import SwiftUI

extension View {
    func onDelete(perform action: @escaping () -> Void) -> some View {
        self.modifier(DeleteListItemModifier(action: action))
    }

}
