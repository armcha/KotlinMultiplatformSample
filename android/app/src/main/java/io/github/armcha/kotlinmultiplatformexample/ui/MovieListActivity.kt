package io.github.armcha.kotlinmultiplatformexample.ui

import android.os.Bundle
import android.util.Log
import io.github.armcha.kotlinmultiplatformexample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.Main
import org.kotlin.mpp.mobile.createApplicationScreenMessage
import org.kotlin.mpp.mobile.di.Injections
import domain.models.Movie
import org.kotlin.mpp.mobile.presentation.movie_list.MovieListContract

class MovieListActivity : BaseActivity<MovieListContract.View, MovieListContract.Presenter>(), MovieListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieName.text = createApplicationScreenMessage()
        presenter.fetchMovieList()
    }

    override fun showError(message: String?) {
        Log.e("showError", "message $message")
    }

    override fun onMovieListReceive(movieList: List<Movie>) {
        Log.e("onMovieListReceive", movieList.size.toString())
        movieList.forEach {
            Log.e("forEach", it.toString())
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun createPresenter(): MovieListContract.Presenter {
        return Injections().provideMovieListPresenter(Dispatchers.Main)
    }
}
