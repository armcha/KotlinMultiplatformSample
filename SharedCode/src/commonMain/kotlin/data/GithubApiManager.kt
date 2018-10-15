package org.kotlin.mpp.mobile.data

import data.CommonApiManager
import io.ktor.client.request.get
import kotlinx.io.core.String
import kotlinx.serialization.json.JSON
import org.kotlin.mpp.mobile.domain.models.User


/**
 *
 * Created by Arman Chatikyan on 14 Oct 2018
 *
 */

class GithubApiManager : CommonApiManager("api.github.com") {

    suspend fun getUser(username: String): User {
        val userResponse = _httpClient.get<String> {
            apiUrl("users/$username")
        }
        return JSON.nonstrict.parse(userResponse)
    }
}