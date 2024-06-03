package com.yudhi.moviedatabase.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yudhi.moviedatabase.api.ApiClient
import com.yudhi.moviedatabase.api.RemoteDataSource
import com.yudhi.moviedatabase.data.MovieRepository
import com.yudhi.moviedatabase.helper.MyDataStore

/*
class MovieViewModelFactory(val remoteDataSource: RemoteDataSource, val pref: MyDataStore) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MovieViewModelFactory? = null

        fun getInstance(context: Context): MovieViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MovieViewModelFactory(
                    RemoteDataSource(ApiClient.instance),
                    MyDataStore
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepository(remoteDataSource), pref) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
 */