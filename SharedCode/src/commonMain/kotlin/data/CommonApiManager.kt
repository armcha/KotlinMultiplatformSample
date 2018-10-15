package data

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.JSON
import org.kotlin.mpp.mobile.domain.models.User

/**
 *
 * Created by Arman Chatikyan on 14 Oct 2018
 *
 */

open class CommonApiManager(private val endPoint: String) {

    protected val _httpClient = HttpClient {
//        install(JsonFeature) {
//            serializer = KotlinxSerializer().apply {
//                setMapper(User::class, User.serializer())
//            }
//        }
//        install(ExpectSuccess)
    }

    protected fun HttpRequestBuilder.apiUrl(path: String) {
        //header(HttpHeaders.CacheControl, "no-cache")
        url {
            protocol = URLProtocol.HTTPS
            host = endPoint
            encodedPath = path
        }
    }
}