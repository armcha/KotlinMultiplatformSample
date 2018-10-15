package org.kotlin.mpp.mobile.data


/**
 *
 * Created by Arman Chatikyan on 14 Oct 2018
 *
 */

interface ResultListener {

    fun <T> onSuccess(result: T)

    fun onError(exception: Exception)
}