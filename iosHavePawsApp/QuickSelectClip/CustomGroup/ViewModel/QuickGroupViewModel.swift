import Foundation
import SwiftUI

class QuickGroupViewModel: ObservableObject {
    @Published var items: [GroupItem] = []
    @Published var showRandomResult = false

    var selectedItemName = ""

    func random() {
        if let random = items.randomElement() {
            selectedItemName = random.name
            showRandomResult = true
        }
    }

    func hideRandomItem() {
        showRandomResult = false
    }

    func addItem(name: String) {
        items.append(
            GroupItem(id: UUID().uuidString, name: name, active: true)
        )
    }

    func deleteItem(item: GroupItem) {
        if let index = items.firstIndex(where: { element in element.id == item.id }) {
            items.remove(at: index)
        }
    }
}
