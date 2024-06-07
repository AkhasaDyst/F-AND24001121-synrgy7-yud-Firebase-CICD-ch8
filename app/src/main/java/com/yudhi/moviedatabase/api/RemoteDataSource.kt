package com.yudhi.moviedatabase.api

import com.yudhi.moviedatabase.data.remote.ApiService

class RemoteDataSource(private val apiService: ApiService) {
    fun getMovieDetail(query: Int) = apiService.getMovieDetail(movieId = query)
    suspend fun moviePopular() = apiService.getMoviePopular()
}
