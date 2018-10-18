package data.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.*
import org.kotlin.mpp.mobile.domain.models.response.OmdbResponse
import org.kotlin.mpp.mobile.log

class OmdbApiManager : CommonApiManager("www.omdbapi.com") {

    suspend fun getMoviePoster(movieImdb:String):OmdbResponse {
        val omdbResponse = httpClient.get<String> {
            parameter("i", movieImdb)
            parameter("apikey","5a90179d")
            apiUrl()
        }

        val response: JsonElement = JsonTreeParser(omdbResponse).read()
        val jsonObject: JsonObject = response.jsonObject
        val omdb = OmdbResponse(
            jsonObject.getKey("Title"),
            jsonObject.getKey("Year"),
            jsonObject.getKey("Released"),
            jsonObject.getKey("Rated"),
            jsonObject.getKey("Runtime"),
            jsonObject.getKey("Genre"),
            jsonObject.getKey("Director"),
            jsonObject.getKey("Writer"),
            jsonObject.getKey("Actors"),
            jsonObject.getKey("Plot"),
            jsonObject.getKey("Language"),
            jsonObject.getKey("Country"),
            jsonObject.getKey("Awards"),
            jsonObject.getKey("Poster")
        )

        return omdb
    }

    override fun withHeaders(): List<Pair<String, String>> {
        return listOf()
    }

    private fun JsonObject.getKey(key:String):String{
        return get(key).content
    }
}