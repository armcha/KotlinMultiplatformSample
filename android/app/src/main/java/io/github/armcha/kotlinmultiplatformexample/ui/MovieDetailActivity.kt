package io.github.armcha.kotlinmultiplatformexample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.squareup.picasso.Picasso
import di.Injections
import domain.models.Movie
import io.github.armcha.kotlinmultiplatformexample.R
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.Dispatchers
import presentation.movie_detail.MovieDetailContract


const val MOVIE_ID_KEY = "movieId"

class MovieDetailActivity : BaseActivity<MovieDetailContract.View, MovieDetailContract.Presenter>(), MovieDetailContract.View {

    override val presenter: MovieDetailContract.Presenter by lazy {
        Injections().provideMovieDetailPresenter(Dispatchers.Main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieId = intent.getStringExtra(MOVIE_ID_KEY)
        presenter.fetchMovieById(movieId)
    }

    override fun onMovieReceive(movie: Movie) {
        Picasso.get()
                .load(movie.poster)
                .into(moviePoster)
        movieTitle.text = movie.title
        movieDescription.text = movie.plot
        cast.text = movie.actors
        date.text = movie.released
    }
}

fun Activity.startMovieActivity(movieId: String) {
    Intent(this, MovieDetailActivity::class.java)
            .apply { putExtra(MOVIE_ID_KEY, movieId) }
            .also { startActivity(it) }
}
