package org.kotlin.mpp.mobile.presentation

import domain.fetcher.result_listener.RequestType
import domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

class MovieListPresenter(uiDispatcher: CoroutineDispatcher, private val movieRepository: MovieRepository) :
    BasePresenter<MovieListContract.View>(uiDispatcher),
    MovieListContract.Presenter {

    override fun fetchMovieList() {
        view?.showLoading()
        fetcher.fetch(movieRepository::getMovieList, RequestType.GET_POPULAR, this) {
            view?.onMovieListReceive(it)
        }
    }

    override fun onRequestError(requestType: RequestType, throwable: Throwable) {
        super.onRequestError(requestType, throwable)
        view?.showError(throwable.message)
    }
}