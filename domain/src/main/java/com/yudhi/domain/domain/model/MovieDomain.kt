package com.yudhi.domain.domain.model

import android.graphics.Movie
import com.yudhi.data.data.remote.response.Dates
import com.yudhi.data.data.remote.response.MovieResponse
import com.yudhi.data.data.remote.response.Result

data class MovieDomain(
    val dates: Dates?,
    val page: Int?,
    val results: List<Result>,
    val totalPages: Int?,
    val totalResults: Int?
)

fun MovieResponse.toMovie(): MovieDomain {
    return MovieDomain(
        dates = dates,
        page = page,
        results = results,
        totalPages = totalPages,
        totalResults = totalResults
    )
}