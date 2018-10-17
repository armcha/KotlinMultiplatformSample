package domain.fetcher

import domain.fetcher.result_listener.RequestType
import domain.fetcher.result_listener.ResultListener
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KSuspendFunction0

/**
 *
 * Created by Arman Chatikyan on 13 Aug 2018
 *
 */

class CoroutineFetcher constructor(private val uiContext: CoroutineDispatcher) : CoroutineScope {

    private val jobMap = HashMap<String, MutableList<Job>>()
    private val requestMap = HashMap<String, HashMap<RequestType, Status>>()

    private val ResultListener.key: String
        get() {
            val kClass = this::class
            return kClass.qualifiedName ?: kClass.simpleName ?: ""
        }

    override val coroutineContext: CoroutineContext
        get() = uiContext

    fun <T> fetch(
        deferred: Deferred<T>, requestType: RequestType,
        resultListener: ResultListener, success: (T) -> Unit
    ) {

        createOrGetJobList(resultListener) += launch(uiContext) {
            resultListener add requestType
            deferred.join()
            if (!(deferred.isCancelled && deferred.isCompleted)) {
                val result = deferred.getCompleted()
                resultListener.onSuccess(requestType, result, success)
            } else {
                val throwable = deferred.getCompletionExceptionOrNull()
                    ?: deferred.getCancellationException()
                resultListener.sendErrorFor(requestType, throwable)
            }
        }
    }

    fun <T> fetch(
        body: suspend () -> T, requestType: RequestType,
        resultListener: ResultListener, success: (T) -> Unit
    ) {
        createOrGetJobList(resultListener) += launch(
            uiContext
                    + exceptionHandler(resultListener, requestType)
        ) {
            resultListener add requestType
            resultListener.onSuccess(requestType, body(), success)
        }
    }

    fun <T> fetch(
        body: KSuspendFunction0<T>, requestType: RequestType,
        resultListener: ResultListener, success: (T) -> Unit
    ) {
        createOrGetJobList(resultListener) += launch(
            uiContext
                    + exceptionHandler(resultListener, requestType)
        ) {
            resultListener add requestType
            resultListener.onSuccess(requestType, body(), success)
        }
    }

    fun complete(
        body: KSuspendFunction0<Unit>, requestType: RequestType,
        resultListener: ResultListener, success: () -> Unit
    ) {
        createOrGetJobList(resultListener) += launch(
            uiContext
                    + exceptionHandler(resultListener, requestType)
        ) {
            resultListener add requestType
            body()
            changeRequestStatus(resultListener, requestType, Status.Success)
            success()
        }
    }

    private fun exceptionHandler(resultListener: ResultListener, requestType: RequestType): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            resultListener.sendErrorFor(requestType, throwable)
        }
    }

    private fun <T> ResultListener.onSuccess(requestType: RequestType, result: T, success: (T) -> Unit) {
        val status = if (result is List<*> && result.isEmpty()) {
            Status.EmptySuccess
        } else {
            Status.Success
        }
        changeRequestStatus(this, requestType, status)
        success(result)
    }

    private fun createOrGetJobList(resultListener: ResultListener): MutableList<Job> {
        return jobMap.getOrPut(resultListener.key) { mutableListOf() }
    }

    infix fun clear(resultListener: ResultListener) {
        val key = resultListener.key
        jobMap.run {
            if (containsKey(key)) {
                this[key]?.forEach { it.cancel() }
                    ?.also { clear() }
                remove(key)
            }
        }
        requestMap.remove(key)
    }

    fun changeRequestStatus(resultListener: ResultListener, requestType: RequestType, status: Status) {
        if (requestMap.containsKey(resultListener.key)) {
            val currentRequest = requestMap[resultListener.key]!! //FIXME
            currentRequest[requestType] = status
            requestMap[resultListener.key] = currentRequest
            //requestMap.replace(key, currentRequest)
        }
    }

    fun getRequestStatus(resultListener: ResultListener, requestType: RequestType): Status {
        val currentRequest = requestMap[resultListener.key]
        return currentRequest?.get(requestType) ?: Status.Idle
    }

    private infix fun ResultListener.add(requestType: RequestType) {
        onRequestStart(requestType)
        if (requestType != RequestType.TYPE_NONE) {
            if (requestMap.containsKey(key)) {
                changeRequestStatus(this, requestType, Status.Loading)
            } else {
                val status = Status.Loading as Status
                requestMap[key] = concurrentMapOf(requestType to status)
            }
        }
    }

    private fun ResultListener.sendErrorFor(requestType: RequestType, throwable: Throwable) {
        changeRequestStatus(this, requestType, Status.Error)
        onRequestError(requestType, throwable)
    }

    private fun <K, V> concurrentMapOf(vararg pair: Pair<K, V>): HashMap<K, V> {
        val map = HashMap<K, V>()
        pair.forEach {
            map[it.first] = it.second
        }
        return map
    }
}