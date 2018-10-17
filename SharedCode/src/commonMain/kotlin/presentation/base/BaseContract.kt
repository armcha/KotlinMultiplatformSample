package org.kotlin.mpp.mobile.presentation.base

interface BaseContract {

    interface View

    interface Presenter<V : View> {

        var view: V?

        fun onPresenterCreate(view: V)

        fun onDestroy()
    }
}

