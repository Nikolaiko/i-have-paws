import SwiftUI
import shared

struct GroupItemElement: View {
    let groupItem: shared.GroupItem
    
    var body: some View {
        Group {
            Text(groupItem.title)
                .foregroundColor(Color.black)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
        .cornerRadius(12.0)
    }
}

struct GroupItemElement_Previews: PreviewProvider {
    static var previews: some View {
        GroupItemElement(groupItem: shared.GroupItem(id: "123", title: "Title"))
    }
}
