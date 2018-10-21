package data.repository

import data.api.OmdbApiManager
import data.api.TraktTvApiManager
import data.cache.MovieCache
import domain.mapper.MovieMapper
import domain.repository.MovieRepository
import domain.models.Movie

class MovieDataRepository constructor(
    private val traktTvApiManager: TraktTvApiManager,
    private val omdbApiManager: OmdbApiManager,
    private val localCache: MovieCache
) : MovieRepository {

    @ExperimentalUnsignedTypes
    override suspend fun getMovieList(movieCount:UInt): List<Movie> {
        val omdbResponseList = traktTvApiManager.getMovieList(movieCount)
            .map { omdbApiManager.getMoviePoster(it) }
        val movieList = MovieMapper.omdbResponseListToMovieList(omdbResponseList)
        localCache.cache(movieList)
        return movieList
    }

    override suspend fun getMovieById(id: String): Movie {
        return localCache.cache.find { it.id == id}!!
    }
}