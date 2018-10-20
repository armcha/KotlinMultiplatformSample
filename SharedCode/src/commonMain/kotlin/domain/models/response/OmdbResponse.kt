package domain.models.response

import kotlinx.serialization.Serializable

@Serializable
data class OmdbResponse(
    val Title: String,
    val Year: String,
    val Released: String,
    val Rated: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String,
    val Language: String,
    val Country: String,
    val Awards: String,
    val Poster: String,
    val imdbID:String)

@Serializable
data class Rating(
    val Source: String,
    val Value: String
)