package org.kotlin.mpp.mobile


import platform.UIKit.UIDevice
import platform.UIKit.UIViewController

actual fun platformName(): String {

    return UIDevice.currentDevice.systemName() +
            " " +
            UIDevice.currentDevice.systemVersion
}
