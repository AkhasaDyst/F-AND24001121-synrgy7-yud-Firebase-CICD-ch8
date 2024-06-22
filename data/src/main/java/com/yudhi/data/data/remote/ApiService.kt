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
        @Header("Authorization") apikey: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTM3NjllYWIxMDlhMDhiM2U0OGU2M2JhYmQ1YTcwMCIsInN1YiI6IjY2NDQ3YTU2YWJlNzU2M2IxNTkxYTcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vb1UXtfdXarecVbH8XHms4taLOrL4pVs-P-iSW9zzlk"
    ): Call<MovieResponse>

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Header("Authorization") apikey: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTM3NjllYWIxMDlhMDhiM2U0OGU2M2JhYmQ1YTcwMCIsInN1YiI6IjY2NDQ3YTU2YWJlNzU2M2IxNTkxYTcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vb1UXtfdXarecVbH8XHms4taLOrL4pVs-P-iSW9zzlk"
    ): MovieResponse

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Header("Authorization") apikey: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTM3NjllYWIxMDlhMDhiM2U0OGU2M2JhYmQ1YTcwMCIsInN1YiI6IjY2NDQ3YTU2YWJlNzU2M2IxNTkxYTcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vb1UXtfdXarecVbH8XHms4taLOrL4pVs-P-iSW9zzlk",
        @Path("movie_id") movieId: Int
    ): Call<Result>

    @POST("movie/{movie_id}/rating")
    fun addRating(
        @Header("Authorization") apikey: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTM3NjllYWIxMDlhMDhiM2U0OGU2M2JhYmQ1YTcwMCIsInN1YiI6IjY2NDQ3YTU2YWJlNzU2M2IxNTkxYTcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vb1UXtfdXarecVbH8XHms4taLOrL4pVs-P-iSW9zzlk",
        @Path("movie_id") movieId: String,
        @Body request: RatingRequest
    ): Call<Responses>

    @GET("search/movie")
    suspend fun searchMovie(
        @Header("Authorization") apikey: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTM3NjllYWIxMDlhMDhiM2U0OGU2M2JhYmQ1YTcwMCIsInN1YiI6IjY2NDQ3YTU2YWJlNzU2M2IxNTkxYTcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vb1UXtfdXarecVbH8XHms4taLOrL4pVs-P-iSW9zzlk",
        @Query("query") query: String
    ): MovieResponse

}