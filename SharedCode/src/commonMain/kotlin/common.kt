package org.kotlin.mpp.mobile

import kotlinx.coroutines.delay

expect fun platformName(): String

fun createApplicationScreenMessage() : String {
    return "Kotlin Rocks on ${platformName()}"
}

fun rand():Int{
    return getRandomNumber()
}

val a = "Hello"
val newB = 90

expect fun getRandomNumber():Int

suspend fun doSomething(){
    delay(1000)
}
