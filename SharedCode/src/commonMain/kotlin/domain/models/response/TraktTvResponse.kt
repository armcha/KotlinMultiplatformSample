package org.kotlin.mpp.mobile.domain.models.response

import kotlinx.serialization.Serializable

/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

@Serializable
data class TraktTvResponse(
    val title: String,
    val year: Int,
    val ids: TraktTvIdsResponse
)

@Serializable
data class TraktTvIdsResponse(
    val trakt: Int,
    val slug: String,
    val imdb: String,
    val tmdb: Int
)