package org.kotlin.mpp.mobile

//import kotlinx.coroutines.delay

expect fun platformName(): String

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}

//suspend fun someFun(){
//    delay(1500)
//}

var logger: Logger? = null

fun log(message: String) {
    logger?.log(message)
}
