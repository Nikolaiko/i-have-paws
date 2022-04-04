import SwiftUI
import SwiftDevPackage
import Combine

struct AddGroupView: View {
    @StateObject private var presenter = AddGroupPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    var body: some View {
        VStack {
            TabTitle(title: "Добавление группы")
            TextField("Имя группы", text: $presenter.groupName)
                .background(Color.white)
                .padding(.vertical, 20)
                .foregroundColor(Color.black)
                .cornerRadius(20)
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
            Spacer()
            
        }
        .background(mainBackgroundColor)
    }
}
