package io.github.armcha.kotlinmultiplatformexample

import android.os.Bundle
import android.util.Log
import data.repository.MovieDataRepository
import data.repository.TraktTvApiManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.Main
import org.kotlin.mpp.mobile.createApplicationScreenMessage
import org.kotlin.mpp.mobile.domain.models.response.TraktTvResponse
import org.kotlin.mpp.mobile.logger
import org.kotlin.mpp.mobile.presentation.MovieListContract
import org.kotlin.mpp.mobile.presentation.MovieListPresenter

class MovieListActivity : BaseActivity<MovieListContract.View, MovieListContract.Presenter>(), MovieListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logger = PlatfromLogger()
        textView.text = createApplicationScreenMessage()
        presenter.fetchMovieList()
    }

    override fun showError(message: String?) {
        Log.e("showError", "message $message")
    }

    override fun onMovieListReceive(movieList: List<TraktTvResponse>) {
        Log.e("onMovieListReceive", movieList.size.toString())
    }

    override fun showLoading() {
        Log.e("showLoading", "showLoading")
    }

    override fun createPresenter(): MovieListContract.Presenter {
        return MovieListPresenter(Dispatchers.Main, MovieDataRepository(TraktTvApiManager()))
    }
}
