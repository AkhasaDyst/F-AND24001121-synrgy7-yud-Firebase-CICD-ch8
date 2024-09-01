package com.yudhi.data.data.remote

import com.yudhi.data.data.remote.request.RatingRequest
import com.yudhi.data.data.remote.response.MovieResponse
import com.yudhi.data.data.remote.response.Responses
import com.yudhi.data.data.remote.response.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call

interface ApiService {
    @GET("movie/now_playing")
    fun getMovieNowPlaying(
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getMoviePopular(
    ): MovieResponse

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): Call<Result>

    @POST("movie/{movie_id}/rating")
    fun addRating(
        @Path("movie_id") movieId: String,
        @Body request: RatingRequest
    ): Call<Responses>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String
    ): MovieResponse

}