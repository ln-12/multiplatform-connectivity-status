//
//  ContentView.swift
//  iosApp
//
//  Created by Lorenzo Neumann on 22.01.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ContentView: View {
    @EnvironmentObject var viewModel: ViewModel

    var body: some View {
        VStack {
            Text("Status: \(viewModel.state.description)")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
