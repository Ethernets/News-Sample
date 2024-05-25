package com.example.news_api

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class News(
    val articles: List<Article>,
)

@Serializable
data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    @Serializable(with = DateSerializer::class)
    val publishedAt: Date?,
    val content: String?,
)


//    @Serializable(with = DateSerializer::class)
//    val publishedAt: Date?,