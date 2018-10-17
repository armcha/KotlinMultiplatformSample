package data.repository

import domain.repository.MovieRepository
import org.kotlin.mpp.mobile.domain.mapper.MovieMapper
import org.kotlin.mpp.mobile.domain.models.Movie

class MovieDataRepository constructor(
    private val traktTvApiManager: TraktTvApiManager,
    private val omdbApiManager: OmdbApiManager
) : MovieRepository {

    override suspend fun getMovieList(): List<Movie> {
        val omdbResponseList = traktTvApiManager.getMovieList()
            .map { omdbApiManager.getMoviePoster(it) }
        return MovieMapper.omdbResponseListToMovieList(omdbResponseList)
    }
}