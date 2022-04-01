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
                PurpleButton(
                    buttonTitle: "Добавить элемент") {
                        showMenu = true
                    }
            }
            .background(mainBackgroundColor)
            .alert(
                isPresented: $showMenu,
                UIAlertModel(
                    title: "Добавить элемент",
                    message: "Длина имени не менее 4 символов",
                    keyboardType: .asciiCapable,
                    action: presenter.tryAddNewGroupItem
                )
            )
        }
    }
}

