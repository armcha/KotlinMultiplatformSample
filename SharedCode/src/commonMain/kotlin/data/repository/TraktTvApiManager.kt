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
            parameter("limit", "1")
            apiUrl("movies/popular")
        }

        //JSON.parseList<>()
        val a = JSON.parseList<TraktTvResponse>(get)
        log("get ${a[0].ids.tmdb}")
        return JSON.parseList<TraktTvResponse>(get)
    }
}