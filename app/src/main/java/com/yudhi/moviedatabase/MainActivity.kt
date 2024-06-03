package com.yudhi.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.yudhi.moviedatabase.api.ApiClient
import com.yudhi.moviedatabase.model.movie.MovieResponse
import com.yudhi.moviedatabase.model.movie.RatingRequest
import com.yudhi.moviedatabase.model.movie.Responses
import com.yudhi.moviedatabase.model.movie.Result
import com.yudhi.moviedatabase.helper.Status
import com.yudhi.moviedatabase.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Navigate to the main destination
        navController.navigate(R.id.loginFragment)
    }
}