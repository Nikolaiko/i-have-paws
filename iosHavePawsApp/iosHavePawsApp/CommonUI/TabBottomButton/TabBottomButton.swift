import SwiftUI

struct TabBottomButton: View {
    let title: String
    let callback: () -> Void
    
    var body: some View {
        Button(action: callback) {
            Text(title)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .foregroundColor(Color.white)
        }
        .background(mainPurpleColor)
        .cornerRadius(12.0)
        .padding(16.0)
    }
}

struct TabBottomButton_Previews: PreviewProvider {
    static var previews: some View {
        TabBottomButton(title: "Test") {}
    }
}
