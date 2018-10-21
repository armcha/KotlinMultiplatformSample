package org.kotlin.mpp.mobile

expect fun platformName(): String

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}

var logger: Logger? = null

fun log(message: String) {
    logger?.log(message)
}

//Strings
object Strings {
    const val alert_error_message = "Something went wrong"
    const val alert_positive_button_text = "Yes"
    const val example_string_id = "Example text"
    const val user_hint = "Please add phone number to continue"
}
