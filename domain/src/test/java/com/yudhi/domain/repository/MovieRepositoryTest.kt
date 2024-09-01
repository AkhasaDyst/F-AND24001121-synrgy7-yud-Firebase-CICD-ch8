package com.yudhi.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yudhi.data.data.remote.RemoteDataSource
import com.yudhi.domain.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        movieRepository = MovieRepository(remoteDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovieDetail() {
        val query = 76479 // misal the boys
        val movies = movieRepository.getMovieDetail(query)

        // Verifikasi hasil tidak null
        assertNotNull(movies)

        // Verifikasi bahwa remoteDataSource.getPopularMovies() dipanggil sekali
        verify { runBlocking { remoteDataSource.getMovieDetail(query) } }

    }

    /*@Test
    suspend fun getMoviePopular() {
        val movies = movieRepository.getMoviePopular()

        // Verifikasi hasil tidak null
        assertNotNull(movies)

        // Verifikasi bahwa remoteDataSource.getPopularMovies() dipanggil sekali
        verify { runBlocking { remoteDataSource.moviePopular() } }
    }*/
}
