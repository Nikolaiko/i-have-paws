import Foundation
import SwiftUI
import Combine

class AddGroupPresenter: ObservableObject {
    
    @Published var groupName = ""
    @Published var addButtonEnabled = false
    
    private var subscriptions: Set<AnyCancellable> = []
    
    init() {
        $groupName
            .sink { [weak self] currentValue in
                self?.addButtonEnabled = self?.validateGroupName(value: currentValue) ?? false
            }
            .store(in: &subscriptions)
    }
    
    func addGroup() {
        
    }
    
    private func validateGroupName(value: String) -> Bool {
        value.count > 4
    }
}
