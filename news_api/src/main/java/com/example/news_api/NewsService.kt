package com.example.news_api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface NewsService {

    @GET("/v2/everything")
    suspend fun everything(): News
}

fun NewsService(apiKey: String): NewsService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Api-Key", apiKey)
                .build()
            chain.proceed(request)}
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
        .build()
        .create(NewsService::class.java)
    return retrofit
}