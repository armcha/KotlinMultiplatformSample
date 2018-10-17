package data.repository

import data.CommonApiManager
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.JSON
import org.kotlin.mpp.mobile.domain.models.Movie
import org.kotlin.mpp.mobile.domain.models.response.TraktTvResponse
import org.kotlin.mpp.mobile.log

//http://www.omdbapi.com/?i=tt1431045&apikey=5a90179d
class OmdbApiManager : CommonApiManager("www.omdbapi.com") {

    suspend fun getMovieList(): List<TraktTvResponse> {
        log("STARTING LOGGER")
        val get = _httpClient.get<String> {
            parameter("limit", "1")
            apiUrl("movies/popular")
        }
        log("get ${get}")
        //JSON.parseList<>()
        val a = JSON.parseList<TraktTvResponse>(get)
        return JSON.parseList<TraktTvResponse>(get)
    }

    suspend fun getMoviePoster(traktTvResponse: TraktTvResponse):Movie{
        val get = _httpClient.get<String> {
            parameter("limit", "1")
            apiUrl("movies/popular")
        }
    }

//    fun getMovieListAsync(context: CoroutineContext, username: String, resultListener: ResultListener) {
//        try {
//            GlobalScope.launch(context = context) {
//                val result: UserFromJson = getUser(username)
//
//                //logger?.log(result.name)
//                resultListener.onSuccess(result)
//                //resultListener.onSuccess(JSON.nonstrict.parse(result))
//            }
//        } catch (ex: Exception) {
//            //logger?.log("Error" + ex.message)
//            resultListener.onError(ex)
//        }
//    }

//    fun getUserAsync(context: CoroutineContext, username: String, resultListener: ResultListener) {
//        try {
//            GlobalScope.launch(context = context) {
//                val result: UserFromJson = getUser(username)
//
//                //logger?.log(result.name)
//                resultListener.onSuccess(result)
//                //resultListener.onSuccess(JSON.nonstrict.parse(result))
//            }
//        } catch (ex: Exception) {
//            //logger?.log("Error" + ex.message)
//            resultListener.onError(ex)
//        }
//    }
}