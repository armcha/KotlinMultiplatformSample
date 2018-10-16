//
//  PlatformLogger.swift
//  KotlinMultiplatformIos
//
//  Created by V-Mobile on 10/16/18.
//  Copyright © 2018 V-Mobile. All rights reserved.
//

import Foundation
import SharedCode

class PlatformLogger:Logger {
    func log(message: String?) {
        print(message)
    }
}
