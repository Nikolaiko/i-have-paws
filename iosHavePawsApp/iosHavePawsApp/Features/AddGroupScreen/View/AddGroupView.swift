import SwiftUI
import SwiftDevPackage
import Combine

struct AddGroupView: View {
    @StateObject private var presenter = AddGroupPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    var body: some View {
        GeometryReader { geom in
            VStack {
                TabTitle(title: "Добавление группы")
                VStack {
                    Spacer()
                    buildMenu(geom: geom)
                    Spacer()
                }
                .padding(.horizontal, geom.size.width * messageBoxSidePaddingCoff)
            }
        }
        .background(blueLight30)
    }
    
    func buildMenu(geom: GeometryProxy) -> some View {
        let viewCornderRadius = geom.size.height * messageBoxCornerRadiusCoff
        let textStyle = InputTextFieldStyle(radius: viewCornderRadius, strokeLineColor: blueLight20, strokeLineWidth: 4.0)
        
        return VStack {
            TextField("Имя группы", text: $presenter.groupName)
                .padding(.all, 5.0)
                .textFieldStyle(textStyle)
                .background(Color.white)
                .foregroundColor(blueLightPrimary)
                .font(inputTextFont)
                .multilineTextAlignment(.center)
                
            ApplicationButton(
                buttonTitle: "Добавить",
                buttonCallback: presenter.addGroup,
                buttonHeight: 50.0
            )
            .disabled(!presenter.addButtonEnabled)
            ApplicationButton(
                buttonTitle: "Назад",
                buttonCallback: { navigation.pop() },
                buttonHeight: 50.0
            )
        }
        .padding(.all, 16.0)
        .background(Color.white)
        .cornerRadius(viewCornderRadius)
    }
}
