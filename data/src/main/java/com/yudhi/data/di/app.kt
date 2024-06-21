package com.yudhi.moviedatabase.di

import android.app.Application
import com.yudhi.moviedatabase.di.KoinModule.dataModule
import com.yudhi.moviedatabase.di.KoinModule.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule, uiModule
                )
            )
        }
    }
}