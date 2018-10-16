package data

import domain.models.UserFromJson
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.URLProtocol
import kotlinx.coroutines.GlobalScope
import org.kotlin.mpp.mobile.domain.models.User

open class CommonApiManager(private val endPoint: String) {

    protected val _httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                //setMapper(User::class, User.serializer())
                setMapper(UserFromJson::class, UserFromJson.serializer())
            }
        }
        install(ExpectSuccess)
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