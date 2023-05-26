//
//  ContentView.swift
//  QuickSelectClip
//
//  Created by Nikolai Baklanov on 25.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    @State var selectedIndex = 0

    var body: some View {
        CustomGroupView()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
