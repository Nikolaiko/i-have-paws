import SwiftUI
import SwiftDevPackage

struct GroupsTab: View {
    @ObservedObject private var presenter = GroupsScreenPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
    @State private var showAddMenu = false
    
    var body: some View {
        GeometryReader { geom in
            VStack {
                TabAddPanel(tapCallback: openAddGroupScreen)
                    .computeFrame(frameSize: geom.size)
                
                TabTitle(title: "Группы")
                List {
                    ForEach(presenter.groupsList, id: \.self) { group in
                        GroupElement(groupName: group.name, deleteGroupCallback: { presenter.deleteGroup(item: group) })
                            .computeFrame(frameSize: geom.size)                            
                            .listRowSeparator(.hidden)
                            .onTapGesture {
                                self.presenter.openGroupScreen(item: group)
                            }
                    }
                    .listRowBackground(transparentColor)
                    .listRowInsets(EdgeInsets())
                }            
            }
            .onAppear {
                presenter.refreshGroupsList()
            }
        }
        .background(mainBackgroundColor)
        .sheet(isPresented: $showAddMenu, onDismiss: onAddMenuDismiss) {
            AddGroupView(
                menuTitle: "Имя группы",
                menuSubtitle: "(не менее 4-х символов)",
                showMenu: $showAddMenu
            )
        }
    }
    
    private func openAddGroupScreen() {
        showAddMenu = true
    }
    
    private func onAddMenuDismiss() {
        presenter.refreshGroupsList()
    }
}
