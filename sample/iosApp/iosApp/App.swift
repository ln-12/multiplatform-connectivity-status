//
//  App.swift
//  iosApp
//
//  Created by Lorenzo Neumann on 02.02.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

@main
struct SteigtUmApp: App {
    @StateObject private var viewModel = ViewModel()

    var body: some Scene {
        WindowGroup {
            ContentView().environmentObject(viewModel)
        }
    }
}

