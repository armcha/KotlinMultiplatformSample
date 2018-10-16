package org.kotlin.mpp.mobile.data

import data.CommonApiManager
import domain.models.UserFromJson
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JSON
import org.kotlin.mpp.mobile.Logger
import org.kotlin.mpp.mobile.domain.models.User
import kotlin.coroutines.CoroutineContext

class GithubApiManager() : CommonApiManager("api.github.com") {

    suspend fun getUser(username: String): UserFromJson {
        val userResponse:UserFromJson = _httpClient.get {
            apiUrl("users/$username")
        }
        return userResponse
    }

    fun getUserAsync(context: CoroutineContext, username: String, resultListener: ResultListener) {
        try {
            GlobalScope.launch(context = context) {
                val result: UserFromJson = getUser(username)

                //logger?.log(result.name)
                resultListener.onSuccess(result)
                //resultListener.onSuccess(JSON.nonstrict.parse(result))
            }
        } catch (ex: Exception) {
            //logger?.log("Error" + ex.message)
            resultListener.onError(ex)
        }
    }
}