import SwiftUI
import shared

struct GroupItemElement: View {
    let groupItem: shared.GroupItem
    @Binding var isActive: Bool
    
    var body: some View {
        HStack {
            Toggle(isOn: $isActive) {
                Text(groupItem.title)
                    .font(groupElementFont)
                    .foregroundColor(blueLightPrimary)
            }
            .tint(blueLight20)
            Spacer()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
        .cornerRadius(12.0)
    }
}
