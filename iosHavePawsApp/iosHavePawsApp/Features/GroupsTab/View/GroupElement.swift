import SwiftUI

struct GroupElement: View {
    let groupName: String
    
    var body: some View {
        HStack {
            Text(groupName)
                .font(groupItemFont)
                .foregroundColor(bluePrimary)
            
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)              
    }
}

struct GroupElement_Previews: PreviewProvider {
    static var previews: some View {
        GroupElement(groupName: "Что надеть?")
    }
}
