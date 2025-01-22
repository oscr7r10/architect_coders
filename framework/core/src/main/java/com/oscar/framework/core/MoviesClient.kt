package com.oscar.framework.core

import com.oscar.framework.movie.network.MoviesService
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

class MoviesClient(private val apiKey: String) {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { apiKeyAsQuery(it) }
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<MoviesService>()

    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain
            .request()
            .newBuilder()
            .url(
                chain.request().url
                    .newBuilder()
                    .addQueryParameter(name = "api_key", apiKey)
                    .build()
            )
            .build()
    )
}