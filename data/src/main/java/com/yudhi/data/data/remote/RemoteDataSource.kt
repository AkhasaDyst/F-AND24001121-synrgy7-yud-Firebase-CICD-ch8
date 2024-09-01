package com.yudhi.data.data.remote

class RemoteDataSource(private val apiService: ApiService) {
    fun getMovieDetail(query: Int) = apiService.getMovieDetail(movieId = query)
    fun moviePopular() = apiService.getMoviePopular()
}
