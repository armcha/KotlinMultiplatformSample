package data

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import org.kotlin.mpp.mobile.domain.models.response.TraktTvIdsResponse
import org.kotlin.mpp.mobile.domain.models.response.TraktTvResponse

open class CommonApiManager(private val endPoint: String) {

    protected val _httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(TraktTvResponse::class, TraktTvResponse.serializer())
                setMapper(TraktTvIdsResponse::class, TraktTvIdsResponse.serializer())
            }
        }
        install(ExpectSuccess)
    }

    protected fun HttpRequestBuilder.apiUrl(path: String) {
        header("trakt-api-key", "077e2b30113120bbf9800f67d4da380276ee59aaebb9c0a8e4a6a75d80eb1b7b")
        header("trakt-api-version", "2")
        url {
            protocol = URLProtocol.HTTPS
            host = endPoint
            encodedPath = path
        }
    }

    fun withHeaders(hea)
}