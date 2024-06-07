package com.yudhi.moviedatabase.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudhi.moviedatabase.api.RemoteDataSource
import com.yudhi.moviedatabase.data.remote.response.toResultDomain
import com.yudhi.moviedatabase.domain.model.Movie
import com.yudhi.moviedatabase.domain.model.ResultDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.yudhi.moviedatabase.data.remote.response.Result

class MovieRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getMoviePopular(): List<ResultDomain>? = remoteDataSource.moviePopular().results?.map { it.toResultDomain() }
    fun getMovieDetail(query: Int): LiveData<ResultDomain?> {
        val result = MutableLiveData<ResultDomain?>()

        remoteDataSource.getMovieDetail(query).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    result.value = response.body()?.toResultDomain()
                } else {
                    result.value = null
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                result.value = null
            }
        })

        return result
    }
}
