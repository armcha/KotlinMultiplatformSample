package data.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.*
import org.kotlin.mpp.mobile.log

class TraktTvApiManager : CommonApiManager("api.trakt.tv") {

    suspend fun getMovieList(): List<String> {
        log("STARTING LOGGER")

        val traktTvResponse = httpClient.get<String> {
            parameter("limit", "5")
            apiUrl("movies/popular")
        }
        val response: JsonArray = JsonTreeParser(traktTvResponse).readFully().jsonArray
        val result: List<String> = response.map {it.jsonObject}
            .map { it.getObject("ids") }
            .map { it.get("imdb") }
            .map { it.content }

        log(traktTvResponse)
        return result
    }

    fun getMovieList(dispatcher: CoroutineDispatcher,resultListener: ListenerMovie){
        resultListener.start()
        GlobalScope.launch(context = dispatcher) {
            resultListener.succes(getMovieList())
        }
    }

    override fun withHeaders(): List<Pair<String, String>> {
        return listOf(
            "trakt-api-key" to "077e2b30113120bbf9800f67d4da380276ee59aaebb9c0a8e4a6a75d80eb1b7b",
            "trakt-api-version" to "2")
    }
}

interface ListenerMovie{

    fun start()

    fun succes(result: List<String>)
}