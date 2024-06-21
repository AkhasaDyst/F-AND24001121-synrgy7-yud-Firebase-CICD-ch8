package com.yudhi.moviedatabase.di

import android.app.Application
import com.yudhi.moviedatabase.api.ApiClient
import com.yudhi.moviedatabase.api.RemoteDataSource
import com.yudhi.moviedatabase.common.data.repository.MovieRepository
import com.yudhi.moviedatabase.common.domain.usecase.MovieUseCase
import com.yudhi.moviedatabase.helper.MyDataStore
import com.yudhi.moviedatabase.presentation.viewmodel.BlurViewModel
import com.yudhi.moviedatabase.presentation.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {
    val Application.dataModule
        get() = module {
            //DATABASE
            single { MyDataStore(androidContext()) }
            //API
            single { ApiClient.instance }
            single { MovieUseCase(get()) }
            //REPOSITORY
            factory { RemoteDataSource(get()) }
            //REPOSITORY
            factory { MovieRepository(get()) }

        }

    val Application.uiModule
        get() = module {
            viewModel { MovieViewModel(get(), get()) }
            viewModel { BlurViewModel(get(), get()) }
        }

}