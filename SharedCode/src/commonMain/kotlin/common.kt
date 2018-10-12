package org.kotlin.mpp.mobile

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
