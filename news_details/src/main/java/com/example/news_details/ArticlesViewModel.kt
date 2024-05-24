package com.example.news_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_api.Article
import com.example.news_api.NewsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ArticlesViewModel(private val newsService: NewsService): ViewModel() {
    val articles = flow<List<Article>> {
        newsService.everything().articles
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}