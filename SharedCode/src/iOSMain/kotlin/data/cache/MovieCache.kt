package data.cache

import org.kotlin.mpp.mobile.domain.models.Movie


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

@ThreadLocal
actual object MovieCache {

    @ThreadLocal
    actual val cache  = mutableListOf<Movie>()

    actual fun cache(movieList: List<Movie>) {
        cache.addAll(movieList)
    }
}