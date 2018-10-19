package presentation.movie_detail

import domain.fetcher.result_listener.RequestType
import domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.kotlin.mpp.mobile.presentation.base.BasePresenter


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

class MovieDetailPresenter(uiDispatcher: CoroutineDispatcher, private val movieRepository: MovieRepository) :
    BasePresenter<MovieDetailContract.View>(uiDispatcher),
        MovieDetailContract.Presenter {

    override fun fetchMovieById(id: String) {
        fetch({movieRepository.getMovieById(id)}, RequestType.GET_MOVIE_BY_ID) {
            view?.onMovieReceive(it)
        }
    }
}