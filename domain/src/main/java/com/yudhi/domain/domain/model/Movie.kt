package com.yudhi.moviedatabase.domain.model
import com.yudhi.moviedatabase.common.data.remote.response.Dates
import com.yudhi.moviedatabase.common.data.remote.response.Result

data class Movie(
    val dates: Dates?,
    val page: Int?,
    val results: List<Result>,
    val totalPages: Int?,
    val totalResults: Int?
)