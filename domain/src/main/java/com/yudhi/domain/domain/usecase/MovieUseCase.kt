package com.yudhi.domain.domain.usecase

import androidx.lifecycle.LiveData
import com.yudhi.domain.domain.model.ResultDomain
import com.yudhi.domain.domain.repository.MovieRepository

class MovieUseCase(val repository: MovieRepository) {
    fun getMovieDetail(movieId: Int): LiveData<ResultDomain?> {
        return repository.getMovieDetail(movieId)
    }

    suspend fun getMoviePopular(): List<ResultDomain>? {
        return repository.getMoviePopular()
    }
}
