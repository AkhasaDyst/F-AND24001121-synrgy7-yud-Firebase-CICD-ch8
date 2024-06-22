package com.yudhi.moviedatabase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.*
import com.yudhi.data.data.remote.response.MovieResponse
import com.yudhi.domain.domain.model.ResultDomain
import com.yudhi.domain.domain.usecase.MovieUseCase
import com.yudhi.domain.helper.MyDataStore
import com.yudhi.domain.common.Resource



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
