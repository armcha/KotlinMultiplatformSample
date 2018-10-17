package io.github.armcha.kotlinmultiplatformexample

import android.app.Application
import io.github.armcha.kotlinmultiplatformexample.util.PlatformLogger
import org.kotlin.mpp.mobile.logger


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        logger = PlatformLogger()
    }
}