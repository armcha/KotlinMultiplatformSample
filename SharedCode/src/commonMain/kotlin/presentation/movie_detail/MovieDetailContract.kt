package presentation.movie_detail

import domain.models.Movie
import org.kotlin.mpp.mobile.presentation.base.BaseContract


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

interface MovieDetailContract {

    interface View : BaseContract.View {

        fun onMovieReceive(movie: Movie)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun fetchMovieById(id:String)
    }
}