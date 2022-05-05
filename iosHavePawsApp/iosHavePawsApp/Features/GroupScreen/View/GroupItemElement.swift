import SwiftUI
import shared

struct GroupItemElement: View {
    let groupItem: shared.GroupItem

    var body: some View {
        GeometryReader { geom in
            HStack {
                Image(groupItem.active ? activeImage : disabledImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .foregroundColor(blueLightPrimary)
                Spacer()
                Text(groupItem.title)
                    .customFont(
                        Montserrat.semiBold,
                        ContentSizeCategory.large,
                        color: blueLightPrimary
                    )
                    .frame(maxWidth: geom.size.width * groupItemTextFieldWidthCoff)
            }
            .padding(.vertical, geom.size.height * groupItemRowInsideVerticalCoff)
            .padding(.horizontal, geom.size.width * groupItemRowInsideHorizontalCoff)
        }
    }
}
