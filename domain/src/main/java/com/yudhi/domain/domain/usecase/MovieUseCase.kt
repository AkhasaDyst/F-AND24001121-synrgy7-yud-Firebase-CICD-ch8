package com.yudhi.moviedatabase.domain.usecase

import androidx.lifecycle.LiveData
import com.yudhi.moviedatabase.common.data.repository.MovieRepository
import com.yudhi.moviedatabase.common.domain.model.ResultDomain

class MovieUseCase(val repository: MovieRepository) {
    fun getMovieDetail(movieId: Int): LiveData<ResultDomain?> {
        return repository.getMovieDetail(movieId)
    }

    suspend fun getMoviePopular(): List<ResultDomain>? {
        return repository.getMoviePopular()
    }
}
