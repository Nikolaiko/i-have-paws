import SwiftUI
import SwiftDevPackage
import Combine

struct AddGroupItemView: View {
    let menuTitle: String
    let menuSubtitle: String
    let groupId: String

    @Binding var showMenu: Bool
    @StateObject private var presenter = AddGroupItemPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel

    var body: some View {
        GeometryReader { geom in
            VStack {
                Spacer()
                Text(menuTitle)
                    .customFont(
                        Montserrat.semiBold,
                        ContentSizeCategory.large,
                        color: blueLightPrimary
                    )
                Text(menuSubtitle)
                    .customFont(
                        Montserrat.semiBold,
                        ContentSizeCategory.small,
                        color: blueLightPrimary
                    )
                buildMenu(geom: geom)
                Spacer()
            }
        }
        .background(Color.white)
    }

    func buildMenu(geom: GeometryProxy) -> some View {
        let viewCornderRadius = geom.size.height * messageBoxCornerRadiusCoff
        let textStyle = InputTextFieldStyle(
            radius: viewCornderRadius,
            strokeLineColor: blueLight20,
            strokeLineWidth: 4.0
        )

        return VStack {
            TextField(menuTitle, text: $presenter.newEntityName)
                .padding(.all, 5.0)
                .textFieldStyle(textStyle)
                .background(Color.white)
                .foregroundColor(blueLightPrimary)
                .font(inputTextFont)
                .multilineTextAlignment(.center)

            ApplicationButton(
                buttonTitle: "Добавить",
                buttonCallback: {
                    presenter.addEntity(groupId: groupId, callback: addEntityCallback)
                },
                buttonHeight: 50.0,
                buttonEnabled: presenter.addButtonEnabled
            )
            .disabled(!presenter.addButtonEnabled)
        }
        .padding(.all, 16.0)
        .background(Color.white)
        .cornerRadius(viewCornderRadius)
    }

    func addEntityCallback(_ error: AppError?) {
        if error == nil {
            showMenu = false
        }
    }
}
