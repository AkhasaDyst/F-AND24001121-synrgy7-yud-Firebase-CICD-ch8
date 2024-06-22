package com.yudhi.moviedatabase.di

import android.app.Application
import com.yudhi.data.data.api.ApiClient
import com.yudhi.data.data.remote.RemoteDataSource
import com.yudhi.domain.domain.repository.MovieRepository
import com.yudhi.domain.domain.usecase.MovieUseCase
import com.yudhi.domain.helper.MyDataStore
import com.yudhi.moviedatabase.presentation.viewmodel.BlurViewModel
import com.yudhi.moviedatabase.presentation.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {
    val Application.dataModule
        get() = module {
            single { ApiClient.instance }
            factory { RemoteDataSource(get()) }
            //DATABASE
            single { MyDataStore(androidContext()) }
            single { MovieUseCase(get()) }
            factory { MovieRepository(get()) }
        }
    val Application.uiModule
        get() = module {
            viewModel { MovieViewModel(get(), get()) }
            viewModel { BlurViewModel(get(), get()) }
        }

}