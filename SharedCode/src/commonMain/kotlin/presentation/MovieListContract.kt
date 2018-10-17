package org.kotlin.mpp.mobile.presentation

import org.kotlin.mpp.mobile.domain.models.Movie
import presentation.BaseContract


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

interface MovieListContract {

    interface View : BaseContract.View {

        fun showLoading()

        fun showError(message: String?)

        fun onMovieListReceive(movieList: List<Movie>)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun fetchMovieList()
    }
}