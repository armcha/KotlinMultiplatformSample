package presentation.movie_list

import domain.fetcher.result_listener.RequestType
import domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import presentation.base.BasePresenter

internal class MovieListPresenter(uiDispatcher: CoroutineDispatcher, private val movieRepository: MovieRepository) :
    BasePresenter<MovieListContract.View>(uiDispatcher),
    MovieListContract.Presenter {

    @ExperimentalUnsignedTypes
    override fun fetchMovieList(movieCount:UInt) {
        fetch({movieRepository.getMovieList(movieCount)}, RequestType.GET_POPULAR) {
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