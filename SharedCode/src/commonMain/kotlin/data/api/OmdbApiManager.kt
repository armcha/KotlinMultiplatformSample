package data.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import domain.models.response.OmdbResponse
import domain.parser.OmdbJsonParser
import util.OMDB_API_KEY
import util.OMDB_URL

internal class OmdbApiManager : CommonApiManager(OMDB_URL) {

    private val API_KEY = "apikey"
    private val IMDB_ID = "i"

    suspend fun getMoviePoster(movieImdb:String): OmdbResponse {
        val omdbResponse = httpClient.get<String> {
            parameter(IMDB_ID, movieImdb)
            parameter(API_KEY, OMDB_API_KEY)
            apiUrl()
        }

        return OmdbJsonParser.parse(omdbResponse)
    }
}