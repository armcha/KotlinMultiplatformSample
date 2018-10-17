package org.kotlin.mpp.mobile.data

import org.kotlin.mpp.mobile.domain.models.response.OmdbResponse


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

object MovieCache {

    val cache = mutableListOf<OmdbResponse>()

    val hasCache
        get() = cache.size != 0

    fun cache(omdbResponse: OmdbResponse) {
        cache += omdbResponse
    }

    fun cache(omdbResponseList: List<OmdbResponse>) {
        clear()
        cache.addAll(omdbResponseList)
    }

    fun clear() {
        cache.clear()
    }
}