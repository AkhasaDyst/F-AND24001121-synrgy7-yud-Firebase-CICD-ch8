package com.yudhi.moviedatabase.di

import android.app.Application
import android.content.Context
import com.yudhi.data.data.api.ContextProvider

class AppContextProvider(private val application: Application) : ContextProvider {
    override fun getContext(): Context {
        return application.applicationContext
    }
}