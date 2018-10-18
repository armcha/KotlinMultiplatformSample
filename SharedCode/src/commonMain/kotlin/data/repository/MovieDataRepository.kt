package data.repository

import data.api.ListenerMovie
import data.api.OmdbApiManager
import data.api.TraktTvApiManager
import domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.data.MovieCache
import org.kotlin.mpp.mobile.domain.mapper.MovieMapper
import org.kotlin.mpp.mobile.domain.models.Movie
import org.kotlin.mpp.mobile.log

class MovieDataRepository constructor(
    private val traktTvApiManager: TraktTvApiManager,
    private val omdbApiManager: OmdbApiManager,
    private val localCache: MovieCache
) : MovieRepository {

    override suspend fun getMovieList(): List<Movie> {
//        val omdbResponseList = if (localCache.hasCache) {
//            localCache.cache
//        } else {
            val omdbResponseList = traktTvApiManager.getMovieList()
                .map { omdbApiManager.getMoviePoster(it) }

            //localCache.cache(omdbResponseList)
            //omdbResponseList
        //}
        return MovieMapper.omdbResponseListToMovieList(omdbResponseList)
    }

    fun getMovieList(dispatcher: CoroutineDispatcher, resultListener: ListenerMovieSecond){
        resultListener.start()
        GlobalScope.launch(context = dispatcher) {
            resultListener.succes(getMovieList())
        }
    }
}

interface ListenerMovieSecond{

    fun start()

    fun succes(result: List<Movie>)
}