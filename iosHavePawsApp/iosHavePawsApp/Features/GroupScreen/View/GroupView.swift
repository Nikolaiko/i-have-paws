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
                        GroupItemElement(groupItem: item, isActive: .constant(true))
                            .frame(maxWidth: .infinity, maxHeight: 84.0)
                            .listRowSeparator(.hidden)
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

