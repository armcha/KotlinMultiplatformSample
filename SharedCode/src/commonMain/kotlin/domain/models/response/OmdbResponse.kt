package org.kotlin.mpp.mobile.domain.models.response

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
    //val Ratings: List<Rating>,
    val Poster: String
//    val Metascore: String,
//    val imdbRating: String,
//    val imdbVotes: String,
//    val imdbID: String,
//    val Type: String,
//    val DVD: String,
//    val BoxOffice: String,
//    val Production: String,
//    val Website: String,
//    val Response: String
   )

@Serializable
data class Rating(
    val Source: String,
    val Value: String
)