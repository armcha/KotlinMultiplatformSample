package data.cache

import org.kotlin.mpp.mobile.domain.models.Movie


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

expect object MovieCache {

    val cache: MutableList<Movie>

    fun cache(movieList: List<Movie>)
}