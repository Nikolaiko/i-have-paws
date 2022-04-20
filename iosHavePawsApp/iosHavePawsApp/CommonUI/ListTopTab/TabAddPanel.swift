import SwiftUI

struct TabAddPanel: View {
    let tapCallback: VoidCallback
    
    var body: some View {
        HStack {
            Spacer()
            Image(systemName: listAddImage)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .foregroundColor(blueLightPrimary)
                .onTapGesture {
                    tapCallback()
                }
        }
        .padding(.trailing, 16.0)
    }
}

struct TabAddPanel_Previews: PreviewProvider {
    static var previews: some View {
        TabAddPanel(tapCallback: { })
    }
}
