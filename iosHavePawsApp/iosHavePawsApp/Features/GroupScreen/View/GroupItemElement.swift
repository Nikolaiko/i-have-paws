import SwiftUI
import shared

struct GroupItemElement: View {
    let groupItem: shared.GroupItem    
    
    var body: some View {
        GeometryReader { geom in
            HStack {
                Text(groupItem.title)
                    .customFont(
                        Montserrat.semiBold,
                        ContentSizeCategory.large,
                        color: blueLightPrimary
                    )
                Spacer()
                Image(groupItem.active ? activeImage : disabledImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .foregroundColor(blueLightPrimary)
                
            }
            .padding(.vertical, geom.size.height * groupItemRowInsideVerticalCoff)
            .padding(.horizontal, geom.size.width * groupItemRowInsideHorizontalCoff)
        }
    }
}
