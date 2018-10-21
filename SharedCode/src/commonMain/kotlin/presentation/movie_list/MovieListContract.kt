package presentation.movie_list

import domain.models.Movie
import org.kotlin.mpp.mobile.presentation.base.BaseContract


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

interface MovieListContract {

    interface View : BaseContract.View {

        fun showLoading()

        fun hideLoading()

        fun showError(message: String?)

        fun onMovieListReceive(movieList: List<Movie>)
    }

    interface Presenter : BaseContract.Presenter<View> {

        @ExperimentalUnsignedTypes
        fun fetchMovieList(movieCount:UInt)
    }
}