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
                TabTitleWithBack(title: presenter.group?.name ?? "")
                ScrollView{
                    LazyVStack {
                        ForEach(presenter.group?.items ?? [], id: \.self) { item in
                            GroupItemElement(groupItem: item, toggleCallback: { b in })
                                .computeFrame(frameSize: geom.size)
                                .onTapGesture {
                                    presenter.updateGroupItemState(item: item)
                                }
                        }                        
                    }
                }
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
                
                ApplicationButton(
                    buttonTitle: "Добавить элемент",
                    buttonCallback: { showMenu = true },
                    buttonWidth: .infinity,
                    buttonHeight: geom.size.height * bottomButtonHeightCoff,
                    buttonColor: blueLightPrimary
                )
                .padding(.horizontal, 16.0)
                .padding(.bottom, 16.0)
            }
            .background(mainBackgroundColor)
            .textAlert(
                isPresented: $showMenu,
                AlertConfiguration(
                    alertTitle: "Добавить элемент",
                    alertMessage: "Длина имени не менее 4 символов",
                    mainAction: presenter.tryAddNewGroupItem,
                    alertKeyboardType: .default
                )
            )
        }
        .toast(message: presenter.errorMessage, isShowing: $presenter.showErrorMessage, duration: shortToastDuration)
        .alert(Text("Та дааам!!!"), isPresented: $presenter.showRandomResult) {
            Button("Ok") {
                presenter.hideRandomItem()
            }
        } message: {
            Text(presenter.selectedItemName)
        }
    }
}

