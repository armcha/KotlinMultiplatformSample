package domain.repository

import org.kotlin.mpp.mobile.domain.models.Movie
import org.kotlin.mpp.mobile.domain.models.response.TraktTvResponse

interface MovieRepository {

    suspend fun getMovieList(): List<TraktTvResponse>
}