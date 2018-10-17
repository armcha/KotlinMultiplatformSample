package data.repository

import domain.repository.MovieRepository
import org.kotlin.mpp.mobile.data.MovieCache
import org.kotlin.mpp.mobile.domain.mapper.MovieMapper
import org.kotlin.mpp.mobile.domain.models.Movie

class MovieDataRepository constructor(
    private val traktTvApiManager: TraktTvApiManager,
    private val omdbApiManager: OmdbApiManager,
    private val localCache: MovieCache
) : MovieRepository {

    override suspend fun getMovieList(): List<Movie> {
        val omdbResponseList = if (localCache.hasCache) {
            localCache.cache
        } else {
            val omdbResponseList = traktTvApiManager.getMovieList()
                .map { omdbApiManager.getMoviePoster(it) }
            localCache.cache(omdbResponseList)
            omdbResponseList
        }
        return MovieMapper.omdbResponseListToMovieList(omdbResponseList)
    }
}