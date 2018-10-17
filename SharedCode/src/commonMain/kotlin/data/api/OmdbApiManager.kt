package data.repository

import data.CommonApiManager
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.JSON
import org.kotlin.mpp.mobile.domain.models.response.OmdbResponse
import org.kotlin.mpp.mobile.domain.models.response.TraktTvResponse

class OmdbApiManager : CommonApiManager("www.omdbapi.com") {

    suspend fun getMoviePoster(traktTvResponse: TraktTvResponse):OmdbResponse {
        val imdb = traktTvResponse.ids.imdb
        val get = httpClient.get<String> {
            parameter("i", imdb)
            parameter("apikey","5a90179d")
            apiUrl()
        }
        return JSON.parse(get)
    }

    override fun withHeaders(): List<Pair<String, String>> {
        return listOf()
    }
}