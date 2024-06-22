package com.yudhi.domain.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudhi.data.data.remote.RemoteDataSource
import com.yudhi.domain.domain.model.ResultDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.yudhi.data.data.remote.response.Result
import com.yudhi.domain.domain.model.toResultDomain

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
