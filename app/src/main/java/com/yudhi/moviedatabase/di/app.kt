package com.yudhi.moviedatabase.di

import android.app.Application
import com.yudhi.data.data.api.ApiClient
import com.yudhi.moviedatabase.di.KoinModule.dataModule
import com.yudhi.moviedatabase.di.KoinModule.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiClient.initialize(AppContextProvider(this))
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    uiModule
                    , dataModule
                )
            )
        }
    }


}