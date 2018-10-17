package org.kotlin.mpp.mobile.presentation

import domain.fetcher.CoroutineFetcher
import domain.fetcher.result_listener.ResultListener
import kotlinx.coroutines.CoroutineDispatcher
import presentation.BaseContract


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

abstract class BasePresenter<V : BaseContract.View>(uiDispatcher: CoroutineDispatcher) : BaseContract.Presenter<V>,ResultListener {

    protected val fetcher: CoroutineFetcher by lazy {
        CoroutineFetcher(uiDispatcher)
    }

    override var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onPresenterCreate() {

    }

    override fun onDestroy() {
        fetcher.clear(this)
    }
}