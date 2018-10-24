package presentation.base

import domain.fetcher.CoroutineFetcher
import domain.fetcher.result_listener.RequestType
import domain.fetcher.result_listener.ResultListener
import kotlinx.coroutines.CoroutineDispatcher
import org.kotlin.mpp.mobile.presentation.base.BaseContract


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

internal abstract class BasePresenter<V : BaseContract.View>(uiDispatcher: CoroutineDispatcher) : BaseContract.Presenter<V>,
    ResultListener {

    private val fetcher: CoroutineFetcher by lazy {
        CoroutineFetcher(uiDispatcher)
    }

    override var view: V? = null

    override fun onPresenterCreate(view: V) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
        fetcher.clear(this)
    }

    fun <T> fetch(body: suspend () -> T, requestType: RequestType, success: (T) -> Unit) {
        fetcher.fetch(body, requestType, this, success)
    }
}