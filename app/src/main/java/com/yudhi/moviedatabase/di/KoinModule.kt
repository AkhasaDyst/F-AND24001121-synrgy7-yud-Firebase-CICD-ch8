package com.yudhi.moviedatabase.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.yudhi.data.data.remote.ApiService
import com.yudhi.moviedatabase.api.ApiClient
import com.yudhi.data.data.remote.RemoteDataSource
import com.yudhi.domain.domain.repository.MovieRepository
import com.yudhi.domain.domain.usecase.MovieUseCase
import com.yudhi.domain.helper.MyDataStore
import com.yudhi.moviedatabase.api.AuthInterceptor
import com.yudhi.moviedatabase.api.Constants
import com.yudhi.moviedatabase.presentation.viewmodel.BlurViewModel
import com.yudhi.moviedatabase.presentation.viewmodel.MovieViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModule {
    val networkModule = module {
        single { AuthInterceptor() }
        single { ChuckerInterceptor(get()) }
        single { provideInterceptor() }
        factory { provideOkHttpClient(get(), get(), get()) }
        single { provideRetrofit(get()) }
        single { provideRetrofitApi(get()) }
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor, chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    fun provideInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideRetrofitApi(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }




    val Application.dataModule
        get() = module {
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