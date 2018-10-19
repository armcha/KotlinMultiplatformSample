package data.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.kotlin.mpp.mobile.domain.parser.TraktTvJsonParser
import util.TRAKT_TV_API_KEY
import util.TRAKT_TV_URL

class TraktTvApiManager : CommonApiManager(TRAKT_TV_URL) {

    private val LIMIT = "limit"
    private val movieLimit = "10"
    private val path = "movies/popular"
    private val apiKey = "trakt-api-key"
    private val apiVersion = "trakt-api-version"

    suspend fun getMovieList(): List<String> {
        val traktTvResponse = httpClient.get<String> {
            parameter(LIMIT,movieLimit)
            apiUrl(path)
        }
        return TraktTvJsonParser.parse(traktTvResponse)
    }

    override fun withHeaders(): List<Pair<String, String>> {
        return listOf(
            apiKey to TRAKT_TV_API_KEY,
            apiVersion to "2")
    }
}