import SwiftUI

struct GroupElement: View {
    let groupName: String
    
    var body: some View {
        HStack {
            Text(groupName)
                .font(groupItemFont)
                .foregroundColor(blueLightPrimary)
            Spacer()            
            Image(deleteListItemImage)
                .resizable()
                .aspectRatio(contentMode: .fit)
        }
        .padding(24.0)
        .frame(maxWidth: .infinity, maxHeight: .infinity)              
    }
}
