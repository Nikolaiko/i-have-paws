import SwiftUI
import shared

struct GroupElement: View {
    let groupItem: shared.Group
    
    var body: some View {
        Group {
            Text(groupItem.name)
                .foregroundColor(Color.black)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
        .cornerRadius(12.0)
    }
}

struct GroupElement_Previews: PreviewProvider {
    static var previews: some View {
        GroupElement(groupItem: shared.Group(id: "id", name: "name", items: []))
    }
}
