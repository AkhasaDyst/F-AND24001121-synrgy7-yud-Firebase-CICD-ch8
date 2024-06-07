package com.yudhi.moviedatabase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.*
import com.yudhi.moviedatabase.api.ApiClient
import com.yudhi.moviedatabase.data.repository.MovieRepository
import com.yudhi.moviedatabase.helper.MyDataStore
import com.yudhi.moviedatabase.common.Resource
import com.yudhi.moviedatabase.data.remote.response.MovieResponse
import com.yudhi.moviedatabase.data.remote.response.Result
import com.yudhi.moviedatabase.domain.model.ResultDomain
import com.yudhi.moviedatabase.domain.usecase.MovieUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MovieViewModel(private val movieUseCase: MovieUseCase, private var pref: MyDataStore) : ViewModel() {

    private var _movieResponse = MutableLiveData<MovieResponse?>()
    val movieResponse: LiveData<MovieResponse?> get() = _movieResponse
    private val _detailMovie = MutableLiveData<ResultDomain?>()
    val detailMovie: LiveData<ResultDomain?> get() = _detailMovie

    fun getMoviePopular() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = movieUseCase.getMoviePopular()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getDetailMovie(movieId: Int): LiveData<Resource<ResultDomain?>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val result = movieUseCase.getMovieDetail(movieId)
            emitSource(result.map { Resource.success(it) })
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
