package org.kotlin.mpp.mobile.domain.models


import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val title: String,
    val year: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val poster: String
)