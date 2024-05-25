package com.example.news_details.di

import android.app.Application
import android.content.Context
import com.example.news_api.NewsService
import com.example.news_details.ArticlesFragment
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
internal annotation class NewsDetailsScope

@Component(dependencies = [NewsDetailsDeps::class], modules = [NewsDetailsModule::class])
@NewsDetailsScope
interface NewsDetailsComponent {
    fun inject(fragment: ArticlesFragment)

    @Component.Builder
    interface Builder{
        fun deps(deps: NewsDetailsDeps): Builder
        fun build(): NewsDetailsComponent

    }
}

@Module
internal class NewsDetailsModule{

}

interface NewsDetailsDepsProvider{
    val newsDetailsDeps: NewsDetailsDeps
}

interface NewsDetailsDeps{
    val newsService: NewsService
}

val Context.newsDetailsDepsProvider: NewsDetailsDepsProvider
    get() = when(this){
        is NewsDetailsDepsProvider -> this
        is Application -> error("Application should implement NewsDetailsDepsProvider")
        else -> applicationContext.newsDetailsDepsProvider
    }