package di

import data.api.OmdbApiManager
import data.api.TraktTvApiManager
import data.cache.MovieCache
import data.repository.MovieDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import presentation.movie_list.MovieListPresenter
import presentation.movie_detail.MovieDetailContract
import presentation.movie_detail.MovieDetailPresenter
import presentation.movie_list.MovieListContract

class Injections {

    private val provideMovieCache by lazy {
        MovieCache
    }

    private val provideTractApiManager = TraktTvApiManager()

    private val provideOmdbApiManager = OmdbApiManager()

    private val provideMovieRepository =
            MovieDataRepository(provideTractApiManager, provideOmdbApiManager, provideMovieCache)

    fun provideMovieListPresenter(uiDispatcher: CoroutineDispatcher): MovieListContract.Presenter {
        return MovieListPresenter(uiDispatcher, provideMovieRepository)
    }

    fun provideMovieDetailPresenter(uiDispatcher: CoroutineDispatcher): MovieDetailContract.Presenter {
        return MovieDetailPresenter(uiDispatcher, provideMovieRepository)
    }

}