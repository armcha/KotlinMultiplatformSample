package org.kotlin.mpp.mobile

expect fun platformName(): String

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}

var logger: Logger? = null

fun log(message: String) {
    logger?.log(message)
}
