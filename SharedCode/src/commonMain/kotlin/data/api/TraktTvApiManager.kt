package data.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.kotlin.mpp.mobile.domain.parser.TraktTvJsonParser

class TraktTvApiManager : CommonApiManager("api.trakt.tv") {

    suspend fun getMovieList(): List<String> {
        val traktTvResponse = httpClient.get<String> {
            parameter("limit", "5")
            apiUrl("movies/popular")
        }
        return TraktTvJsonParser.parse(traktTvResponse)
    }

    override fun withHeaders(): List<Pair<String, String>> {
        return listOf(
            "trakt-api-key" to "077e2b30113120bbf9800f67d4da380276ee59aaebb9c0a8e4a6a75d80eb1b7b",
            "trakt-api-version" to "2")
    }
}