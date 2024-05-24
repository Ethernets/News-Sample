package com.example.news_details.di

import com.example.news_api.NewsService
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(dependencies = [NewsDetailsDeps::class], modules = [NewsDetailsModule::class])
@NewsDetailsScope
interface NewsDetailsComponent {
}

@Module
internal class NewsDetailsModule{

}
interface NewsDetailsDeps{
    val newsService: NewsService
}

@Scope
annotation class NewsDetailsScope