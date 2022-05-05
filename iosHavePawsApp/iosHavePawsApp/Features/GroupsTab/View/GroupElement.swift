import SwiftUI

struct GroupElement: View {
    let groupName: String
    let deleteGroupCallback: VoidCallback

    var body: some View {
        GeometryReader { geom in
            HStack {
                Text(groupName)
                    .customFont(
                        Montserrat.semiBold,
                        ContentSizeCategory.large,
                        color: blueLightPrimary
                    )
                Spacer()
                Image(deleteListItemImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .onTapGesture { deleteGroupCallback() }
            }
            .padding(.vertical, geom.size.height * groupRowInsideVerticalCoff)
            .padding(.horizontal, geom.size.width * groupRowInsideHorizontalCoff)
        }
    }
}
