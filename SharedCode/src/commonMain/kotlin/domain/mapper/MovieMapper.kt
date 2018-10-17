package org.kotlin.mpp.mobile.domain.mapper

import org.kotlin.mpp.mobile.domain.models.Movie
import org.kotlin.mpp.mobile.domain.models.response.OmdbResponse


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

object MovieMapper {

    private fun omdbResponseToMovie(omdbResponse: OmdbResponse): Movie {
        return with(omdbResponse) {
            Movie(Title, Year, Released, Runtime, Genre, Director, Writer, Actors, Plot, Poster)
        }
    }

    fun omdbResponseListToMovieList(omdbResponseList: List<OmdbResponse>): List<Movie> {
        return omdbResponseList.map {
            omdbResponseToMovie(it)
        }
    }
}