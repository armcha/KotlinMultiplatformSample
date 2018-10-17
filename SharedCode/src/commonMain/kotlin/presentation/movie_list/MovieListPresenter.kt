package org.kotlin.mpp.mobile.presentation.movie_list

import domain.fetcher.result_listener.RequestType
import domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.kotlin.mpp.mobile.presentation.base.BasePresenter
import org.kotlin.mpp.mobile.presentation.movie_list.MovieListContract


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

class MovieListPresenter(uiDispatcher: CoroutineDispatcher, private val movieRepository: MovieRepository) :
    BasePresenter<MovieListContract.View>(uiDispatcher),
    MovieListContract.Presenter {

    override fun fetchMovieList() {
        fetch(movieRepository::getMovieList, RequestType.GET_POPULAR) {
            view?.hideLoading()
            view?.onMovieListReceive(it)
        }
    }

    override fun onRequestStart(requestType: RequestType) {
        super.onRequestStart(requestType)
        view?.showLoading()
    }

    override fun onRequestError(requestType: RequestType, throwable: Throwable) {
        super.onRequestError(requestType, throwable)
        view?.showError(throwable.message)
        view?.hideLoading()
    }
}