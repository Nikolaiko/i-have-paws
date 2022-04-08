import SwiftUI
import SwiftDevPackage

struct GroupsTab: View {
    @ObservedObject private var presenter = GroupsScreenPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    var body: some View {
        GeometryReader { geom in
            VStack {
                TabTitle(title: "Группы")
                let _ = print(geom.size.height * groupItemCornerCoff)
                List {
                    ForEach(presenter.groupsList, id: \.self) { group in
                        GroupElement(groupName: group.name)
                            .computeFrame(frameSize: geom.size)                            
                            .listRowSeparator(.hidden)
                            .onTapGesture {
                                self.presenter.openGroupScreen(item: group)
                            }
                    }
                    .listRowBackground(transparentColor)
                    .listRowInsets(EdgeInsets())
                }
                
                Spacer()
                ApplicationButton(
                    buttonTitle: "Добавить группу",
                    buttonCallback: { navigation.push(AddGroupView()) },
                    buttonWidth: .infinity,
                    buttonHeight: 50.0,
                    buttonColor: mainPurpleColor
                )
                .padding(.horizontal, 16.0)
                .padding(.bottom, 16.0)
            }
            .onAppear {
                presenter.refreshGroupsList()
            }
        }
        .background(mainBackgroundColor)
    }
}
