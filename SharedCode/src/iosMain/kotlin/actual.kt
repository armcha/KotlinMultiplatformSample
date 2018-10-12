package org.kotlin.mpp.mobile

import platform.UIKit.UIDevice

actual fun platformName(): String {
    return UIDevice.currentDevice.systemName() +
            " " +
            UIDevice.currentDevice.systemVersion
}

actual fun getRandomNumber():Int{
    return 220
}