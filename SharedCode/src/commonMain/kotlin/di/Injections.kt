package org.kotlin.mpp.mobile.di

import data.api.OmdbApiManager
import data.api.TraktTvApiManager
import data.repository.MovieDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import data.cache.MovieCache
import org.kotlin.mpp.mobile.presentation.movie_list.MovieListPresenter

class Injections {

//    val movieListPresenter:MovieListPresenter
//        get() {
//            MovieListPresenter()
//        }


    private val provideMovieCache by lazy {
        MovieCache
    }

    private val provideTractApiManager = TraktTvApiManager()

    private val provideOmdbApiManager = OmdbApiManager()

    private val provideMovieRepository =
        MovieDataRepository(provideTractApiManager, provideOmdbApiManager, provideMovieCache)

    fun provideMovieListPresenter(uiDispatcher: CoroutineDispatcher): MovieListPresenter {
        return MovieListPresenter(uiDispatcher, provideMovieRepository)
    }

}