package domain.models

data class Movie(
    val id:String,
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