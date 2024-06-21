package com.yudhi.moviedatabase.data

import com.yudhi.moviedatabase.common.data.remote.ApiService

class RemoteDataSource(private val apiService: ApiService) {
    fun getMovieDetail(query: Int) = apiService.getMovieDetail(movieId = query)
    suspend fun moviePopular() = apiService.getMoviePopular()
}
