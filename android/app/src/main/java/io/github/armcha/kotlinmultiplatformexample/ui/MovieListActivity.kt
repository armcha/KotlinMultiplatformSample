package io.github.armcha.kotlinmultiplatformexample.ui

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import di.Injections
import domain.models.Movie
import io.github.armcha.kotlinmultiplatformexample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.Main
import presentation.movie_list.MovieListContract


class MovieListActivity : BaseActivity<MovieListContract.View, MovieListContract.Presenter>(), MovieListContract.View {

    override val presenter: MovieListContract.Presenter by lazy {
        Injections().provideMovieListPresenter(Dispatchers.Main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.fetchMovieList()
    }

    override fun showError(message: String?) {
        AlertDialog.Builder(this).setMessage("Something went wrong")
                .setPositiveButton("Yes", null)
                .create()
                .show()
    }

    override fun onMovieListReceive(movieList: List<Movie>) {
        movieRecyclerView setUpWith MovieListAdapter(movieList, object : ItemCLickListener {
            override fun onItemClick(movieId: String) {
                startMovieActivity(movieId)
            }
        })
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
