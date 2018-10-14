package org.kotlin.mpp.mobile

import kotlin.random.Random
import kotlin.random.nextInt

actual fun platformName(): String {
    return "Android"
}

actual fun getRandomNumber():Int{

    return Random.nextInt(0..100)
}
