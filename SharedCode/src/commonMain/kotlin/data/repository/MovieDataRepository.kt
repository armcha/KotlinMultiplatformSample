package data.repository

import data.api.OmdbApiManager
import data.api.TraktTvApiManager
import domain.repository.MovieRepository
import data.cache.MovieCache
import org.kotlin.mpp.mobile.domain.mapper.MovieMapper
import org.kotlin.mpp.mobile.domain.models.Movie

class MovieDataRepository constructor(
    private val traktTvApiManager: TraktTvApiManager,
    private val omdbApiManager: OmdbApiManager,
    private val localCache: MovieCache
) : MovieRepository {

    override suspend fun getMovieList(): List<Movie> {
        val omdbResponseList = traktTvApiManager.getMovieList()
            .map { omdbApiManager.getMoviePoster(it) }
        //localCache.cache(omdbResponseList)
        return MovieMapper.omdbResponseListToMovieList(omdbResponseList)
    }
}