package com.yudhi.moviedatabase

import android.app.Application
import com.yudhi.moviedatabase.api.ApiClient
import com.yudhi.moviedatabase.api.RemoteDataSource
import com.yudhi.moviedatabase.data.MovieRepository
import com.yudhi.moviedatabase.helper.MyDataStore
import com.yudhi.moviedatabase.viewmodel.BlurViewModel
import com.yudhi.moviedatabase.viewmodel.MovieViewModel
import org.koin.android.compat.ScopeCompat.viewModel
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