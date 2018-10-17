package data.repository

import data.CommonApiManager
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.JSON
import org.kotlin.mpp.mobile.domain.models.response.TraktTvResponse
import org.kotlin.mpp.mobile.log

class TraktTvApiManager : CommonApiManager("api.trakt.tv") {

    suspend fun getMovieList(): List<TraktTvResponse> {
        log("STARTING LOGGER")
        val get = _httpClient.get<String> {
            parameter("limit", "50")
            apiUrl("movies/popular")
        }
        return JSON.parseList(get)
    }

    override fun withHeaders(): List<Pair<String, String>> {
        return listOf(
            "trakt-api-key" to "077e2b30113120bbf9800f67d4da380276ee59aaebb9c0a8e4a6a75d80eb1b7b",
            "trakt-api-version" to "2")
    }
}