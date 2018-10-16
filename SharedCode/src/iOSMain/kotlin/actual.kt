package org.kotlin.mpp.mobile


import kotlinx.coroutines.GlobalScope
import platform.UIKit.UIDevice
import platform.UIKit.UIViewController

import kotlinx.coroutines.launch

//
//class H: UIViewController(){
//
//    external override fun viewDidLoad() {
//        super.viewDidLoad()
//    }
//}



actual fun platformName(): String {

    return UIDevice.currentDevice.systemName() +
            " " +
            UIDevice.currentDevice.systemVersion
}