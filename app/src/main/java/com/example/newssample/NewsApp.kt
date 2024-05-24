package com.example.newssample

import android.app.Application
import com.example.newssample.di.AppComponent
import com.example.newssample.di.DaggerAppComponent

class NewsApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .apiKey("2577533b9ce848039425bb57d6f2616b")
            .build()
    }
    override fun onCreate() {
        super.onCreate()
    }
}