import SwiftUI

struct CustomGroupView: View {
    @StateObject var viewModel = QuickGroupViewModel()
    @State var showAddMenu = false
    @State var newItemName = ""

    var body: some View {
        GeometryReader { geom in
            VStack {
                ScrollView {
                    LazyVStack {
                        ForEach(viewModel.items) { item in
                            QuickGroupItem(groupItem: item)
                                .onDelete {
                                    viewModel.deleteItem(item: item)
                                }
                                .frame(
                                    width: geom.size.width * 1.0,
                                    height: geom.size.height * 0.0837
                                )
                        }
                    }
                }
                Spacer()
                Button {
                    viewModel.random()
                } label: {
                    Image(systemName: randomButtonImage)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: geom.size.width / 8)

                }
                .padding(.bottom, 16)
                .disabled(viewModel.items.isEmpty)
                ApplicationButton(
                    buttonTitle: "Добавить",
                    buttonCallback: {
                        newItemName = ""
                        showAddMenu = true
                    },
                    buttonHeight: geom.size.height * 0.06
                )
            }
            .alert("Введите имя нового элемента", isPresented: $showAddMenu) {
                TextField("название", text: $newItemName)
                Button("Добавить", action: { viewModel.addItem(name: newItemName)
                })
            }
            .alert(Text("Та дааам!!!"), isPresented: $viewModel.showRandomResult) {
                Button("Ok") {
                    viewModel.hideRandomItem()
                }
            } message: {
                Text(viewModel.selectedItemName)
            }
        }
    }
}

struct CustomGroupView_Previews: PreviewProvider {
    static var previews: some View {
        CustomGroupView()
    }
}
