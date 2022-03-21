import SwiftUI
import SwiftDevPackage

struct GroupsTab: View {
    @ObservedObject private var presenter = GroupScreenPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    var body: some View {
        GeometryReader { geom in
            VStack {
                TabTitle(title: "Группы")
                List {
                    ForEach(presenter.groupsList, id: \.self) { group in
                        GroupElement(groupItem: group)
                            .frame(maxWidth: .infinity, maxHeight: 84.0)
                    }
                    .listRowBackground(transparentColor)
                    .listRowInsets(EdgeInsets())
                }
                
                Spacer()
                TabBottomButton(
                    title: "Добавить группу",
                    callback: { navigation.push(AddGroupView()) }
                )
                .frame(maxWidth: .infinity, maxHeight: 100.0)
                
            }
            .onAppear {
                presenter.refreshGroupsList()
            }
        }
        .background(mainBackgroundColor)
    }
}

struct GroupsScreen_Previews: PreviewProvider {
    static var previews: some View {
        GroupsTab()
    }
}
