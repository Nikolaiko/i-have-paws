import SwiftUI
import SwiftDevPackage

struct AddGroupView: View {
    @ObservedObject private var presenter = AddGroupPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    var body: some View {
        VStack {
            TabTitle(title: "Добавление группы")
            TextField("Имя группы", text: $presenter.groupName)
                .background(Color.white)
                .padding(.vertical, 20)
                .foregroundColor(Color.black)
                .cornerRadius(20)
            Button {
                
            } label: {
                Text("Добавить")                    
            }
            .disabled(!presenter.addButtonEnabled)
            Spacer()
            
        }
        .background(mainBackgroundColor)
    }
}

struct AddGroupView_Previews: PreviewProvider {
    static var previews: some View {
        AddGroupView()
    }
}
