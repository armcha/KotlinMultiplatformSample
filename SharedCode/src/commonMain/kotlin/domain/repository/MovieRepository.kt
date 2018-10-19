package domain.repository

import domain.models.Movie

interface MovieRepository {

    suspend fun getMovieList(): List<Movie>

    suspend fun getMovieById(id:String): Movie
}