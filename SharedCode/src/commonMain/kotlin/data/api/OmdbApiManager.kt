package data.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.kotlin.mpp.mobile.domain.models.response.OmdbResponse
import org.kotlin.mpp.mobile.domain.parser.OmdbJsonParser

class OmdbApiManager : CommonApiManager("www.omdbapi.com") {

    suspend fun getMoviePoster(movieImdb:String):OmdbResponse {
        val omdbResponse = httpClient.get<String> {
            parameter("i", movieImdb)
            parameter("apikey","5a90179d")
            apiUrl()
        }

        return OmdbJsonParser.parse(omdbResponse)
    }
}