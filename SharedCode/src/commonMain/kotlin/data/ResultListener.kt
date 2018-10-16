package org.kotlin.mpp.mobile.data

import org.kotlin.mpp.mobile.domain.models.User


/**
 *
 * Created by Arman Chatikyan on 14 Oct 2018
 *
 */

interface ResultListener{

    fun onSuccess(result: Any)

    fun onError(exception: Exception)
}