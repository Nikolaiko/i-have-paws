import SwiftUI
import SwiftDevPackage

struct GroupsTab: View {
    @ObservedObject private var presenter = GroupsScreenPresenter()
    @EnvironmentObject private var navigation: NavigationControllerViewModel
    
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
    }
    
    private func openAddGroupScreen() {
        navigation.push(AddGroupView())
    }
}
