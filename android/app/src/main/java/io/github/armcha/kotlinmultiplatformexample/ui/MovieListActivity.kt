package io.github.armcha.kotlinmultiplatformexample.ui

import android.os.Bundle
import android.util.Log
import data.repository.MovieDataRepository
import data.api.OmdbApiManager
import data.api.TraktTvApiManager
import io.github.armcha.kotlinmultiplatformexample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.Main
import org.kotlin.mpp.mobile.createApplicationScreenMessage
import org.kotlin.mpp.mobile.data.MovieCache
import org.kotlin.mpp.mobile.domain.models.Movie
import org.kotlin.mpp.mobile.presentation.movie_list.MovieListContract
import org.kotlin.mpp.mobile.presentation.movie_list.MovieListPresenter

class MovieListActivity : BaseActivity<MovieListContract.View, MovieListContract.Presenter>(), MovieListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = createApplicationScreenMessage()
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
        return MovieListPresenter(Dispatchers.Main, MovieDataRepository(TraktTvApiManager(), OmdbApiManager(),MovieCache))
    }
}
