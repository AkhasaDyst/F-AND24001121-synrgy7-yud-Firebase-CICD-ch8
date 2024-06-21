package com.yudhi.moviedatabase.data.remote.response

import com.google.gson.annotations.SerializedName
import com.yudhi.moviedatabase.common.data.remote.response.Dates
import com.yudhi.moviedatabase.common.data.remote.response.MovieResponse
import com.yudhi.moviedatabase.common.data.remote.response.Result
import com.yudhi.moviedatabase.common.domain.model.Movie

data class MovieResponse(
    @SerializedName("dates")
    val dates: Dates?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

fun MovieResponse.toMovie(): Movie {
    return Movie(
         dates = dates,
         page = page,
         results = results,
         totalPages = totalPages,
         totalResults = totalResults
    )
}
