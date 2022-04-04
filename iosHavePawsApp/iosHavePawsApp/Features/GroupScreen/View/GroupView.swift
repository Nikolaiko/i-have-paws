import SwiftUI
import shared
import Resolver

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
                List {
                    ForEach(presenter.group?.items ?? [], id: \.self) { item in
                        GroupItemElement(groupItem: item)
                            .frame(maxWidth: .infinity, maxHeight: 84.0)
                            .onTapGesture {
                                
                            }
                    }
                    .listRowBackground(transparentColor)
                    .listRowInsets(EdgeInsets())
                }
                Spacer()
                ApplicationButton(
                    buttonTitle: "РАНДОМ!!!",
                    buttonCallback: presenter.selectRandomItem,
                    buttonWidth: .infinity,
                    buttonHeight: 50.0,
                    buttonEnabled: presenter.enableRandomButton,
                    buttonColor: mainPurpleColor
                )
                .padding(.horizontal, 16.0)
                
                ApplicationButton(
                    buttonTitle: "Добавить элемент",
                    buttonCallback: { showMenu = true },
                    buttonWidth: .infinity,
                    buttonHeight: 50.0,
                    buttonColor: mainPurpleColor
                )
                .padding(.horizontal, 16.0)
                
                ApplicationButton(
                    buttonTitle: "Удалить группу",
                    buttonCallback: {  },
                    buttonWidth: .infinity,
                    buttonHeight: 50.0,
                    buttonColor: mainRedButtonColor
                )
                .padding(.horizontal, 16.0)
                .padding(.bottom, 8.0)
            }
            .background(mainBackgroundColor)
            .alert(
                isPresented: $showMenu,
                UIAlertModel(
                    title: "Добавить элемент",
                    message: "Длина имени не менее 4 символов",
                    keyboardType: .default,
                    action: presenter.tryAddNewGroupItem
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

