package data.api

import domain.parser.TraktTvJsonParser
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import util.TRAKT_TV_API_KEY
import util.TRAKT_TV_URL

class TraktTvApiManager : CommonApiManager(TRAKT_TV_URL) {

    private val LIMIT = "limit"
    private val path = "movies/popular"
    private val apiKey = "trakt-api-key"
    private val apiVersion = "trakt-api-version"

    @ExperimentalUnsignedTypes
    suspend fun getMovieList(movieCount:UInt): List<String> {
        val traktTvResponse = httpClient.get<String> {
            parameter(LIMIT,movieCount.toString())
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