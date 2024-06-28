package com.yudhi.data.data.api

import android.content.Context

// In your data module, e.g., in a file called ContextProvider.kt
interface ContextProvider {
    fun getContext(): Context
}
