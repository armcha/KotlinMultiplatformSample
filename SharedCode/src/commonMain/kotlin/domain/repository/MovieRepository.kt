package domain.repository

import domain.models.Movie

internal interface MovieRepository {

    @ExperimentalUnsignedTypes
    suspend fun getMovieList(movieCount:UInt): List<Movie>

    suspend fun getMovieById(id:String): Movie
}