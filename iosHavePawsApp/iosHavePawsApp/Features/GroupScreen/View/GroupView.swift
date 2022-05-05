import SwiftUI
import shared
import Resolver
import iOSServices

struct GroupView: View {
    private let group: shared.Group

    @State private var showMenu = false
    @ObservedObject private var presenter = GroupScreenPresenter()

    init(viewGroup: shared.Group) {
        group = viewGroup
        presenter.initWithGroup(item: group)
    }

    var body: some View {
        GeometryReader { geom in
            VStack {
                TabAddPanel(tapCallback: { showMenu = true })
                    .computeFrame(frameSize: geom.size)

                TabTitleWithBack(title: presenter.group?.name ?? "")
                ScrollView {
                    LazyVStack {
                        ForEach(presenter.group?.items ?? [], id: \.self) { item in
                            GroupItemElement(groupItem: item)
                                .computeFrame(frameSize: geom.size)
                                .onTapGesture {
                                    presenter.updateGroupItemState(item: item)
                                }
                                .onDelete {
                                    presenter.deleteGroupItem(item: item)
                                }
                        }
                    }
                }
            }
            .floatingActionButton(
                color: presenter.enableRandomButton ? blueLightPrimary : mainDisabledButtonColor,
                image: Image(systemName: randomButtonImage),
                action: presenter.enableRandomButton ? presenter.selectRandomItem : { }
            )
        }
        .background(mainBackgroundColor)
        .toast(message: presenter.errorMessage, isShowing: $presenter.showErrorMessage, duration: shortToastDuration)
        .sheet(isPresented: $showMenu, onDismiss: onAddMenuDismiss) {
            AddGroupItemView(
                menuTitle: "Имя элемента",
                menuSubtitle: "(не менее 4-х символов)",
                groupId: group.id,
                showMenu: $showMenu
            )
        }
        .alert(Text("Та дааам!!!"), isPresented: $presenter.showRandomResult) {
            Button("Ok") {
                presenter.hideRandomItem()
            }
        } message: {
            Text(presenter.selectedItemName)
        }
    }

    private func buildMainContent(_ geom: GeometryProxy) -> some View {
        return VStack {
            TabAddPanel(tapCallback: { showMenu = true })
                .computeFrame(frameSize: geom.size)

            TabTitleWithBack(title: presenter.group?.name ?? "")
            ScrollView {
                LazyVStack {
                    ForEach(presenter.group?.items ?? [], id: \.self) { item in
                        GroupItemElement(groupItem: item)
                            .computeFrame(frameSize: geom.size)
                            .onTapGesture {
                                presenter.updateGroupItemState(item: item)
                            }
                            .onDelete {
                                presenter.deleteGroupItem(item: item)
                            }
                    }
                }
            }
        }
    }

    private func buildBottomFloatingButton(_ geom: GeometryProxy) -> some View {
        return VStack {
            Spacer()
            ApplicationButton(
                buttonTitle: "РАНДОМ!!!",
                buttonCallback: presenter.selectRandomItem,
                buttonWidth: .infinity,
                buttonHeight: geom.size.height * bottomButtonHeightCoff,
                buttonEnabled: presenter.enableRandomButton,
                buttonColor: blueLightPrimary
            )
            .padding(.horizontal, 16.0)
            .padding(.horizontal, 16.0)
            .padding(.bottom, 16.0)
        }
    }

    private func onAddMenuDismiss() {
        presenter.refreshGroup()
    }
}
