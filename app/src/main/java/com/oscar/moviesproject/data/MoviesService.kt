package com.oscar.moviesproject.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie?sort_by=popularity.des")
    suspend fun fetchPopularMovies(@Query("region") region: String) : RemoteResult

    @GET("movie/{id}")
    suspend fun fetchMovieById(@Path("id") id: Int): RemoteMovie

}