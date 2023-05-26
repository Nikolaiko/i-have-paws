//
//  GroupItem.swift
//  QuickSelectClip
//
//  Created by Nikolai Baklanov on 25.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct GroupItem: Identifiable {
    let id: String
    let name: String
    let active: Bool

    func copyWith(newId: String?, newName: String?, newActive: Bool?) -> GroupItem {
        GroupItem(id: newId ?? id, name: newName ?? name, active: newActive ?? active)
    }
}
