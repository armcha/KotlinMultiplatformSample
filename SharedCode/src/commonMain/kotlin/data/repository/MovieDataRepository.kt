package data.repository

import domain.repository.MovieRepository
import org.kotlin.mpp.mobile.domain.models.response.TraktTvResponse

class MovieDataRepository constructor(private val traktTvApiManager: TraktTvApiManager) : MovieRepository {

    override suspend fun getMovieList(): List<TraktTvResponse> {
        return traktTvApiManager.getMovieList()
    }
}