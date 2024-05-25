package com.example.news_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.news_details.di.DaggerNewsDetailsComponent
import com.example.news_details.di.NewsDetailsComponent
import com.example.news_details.di.newsDetailsDepsProvider

internal class NewsComponentViewModel(application: Application): AndroidViewModel(application){
    val newsDetailsComponent: NewsDetailsComponent by lazy {
        DaggerNewsDetailsComponent.builder()
            .deps(application.newsDetailsDepsProvider.newsDetailsDeps)
            .build()
    }
}