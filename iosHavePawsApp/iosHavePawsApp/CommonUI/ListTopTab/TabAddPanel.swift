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
                .accessibilityLabel(addEntityButtonLabel)
                .onTapGesture {
                    tapCallback()
                }
        }
        .padding(.trailing, topAddButtonTrailing)
    }
}

struct TabAddPanel_Previews: PreviewProvider {
    static var previews: some View {
        TabAddPanel(tapCallback: { })
    }
}
