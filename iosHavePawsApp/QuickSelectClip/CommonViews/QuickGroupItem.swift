//
//  QuickGroupItem.swift
//  QuickSelectClip
//
//  Created by Nikolai Baklanov on 25.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct QuickGroupItem: View {
    let groupItem: GroupItem

    var body: some View {
        GeometryReader { geom in
            HStack {
                Image(groupItem.active ? activeImage : disabledImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .foregroundColor(Color.blue)
                Spacer()
                Text(groupItem.name)
                    .customFont(
                        Montserrat.semiBold,
                        ContentSizeCategory.large,
                        color: Color.blue
                    )
                    .frame(maxWidth: geom.size.width * groupItemTextFieldWidthCoff)
            }
            .padding(.vertical, geom.size.height * 0.2222)
            .padding(.horizontal, geom.size.width * 0.07339)
        }
    }
}

struct QuickGroupItem_Previews: PreviewProvider {
    static var previews: some View {
        QuickGroupItem(groupItem: GroupItem(id: "123", name: "Example", active: true))
    }
}
