//
//  ViewModel.swift
//  iosApp
//
//  Created by Lorenzo Neumann on 22.01.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

final class ViewModel: NSObject, ObservableObject {
    private let sharedStatus: SharedStatus = SharedStatus()
    
    @Published var state: Bool = false
    
    override init() {
        super.init()

        sharedStatus.getStatus(success: { status in
            self.state = status.boolValue
        })

        sharedStatus.start()
    }
}
