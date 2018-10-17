package io.github.armcha.kotlinmultiplatformexample.util

import android.util.Log
import org.kotlin.mpp.mobile.Logger


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

class PlatformLogger : Logger {

    override fun log(message: String?) {
        Log.e("Tag", message)
    }
}