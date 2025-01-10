package com.oscar.data.datasource

import com.oscar.domain.Movie

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<com.oscar.domain.Movie>

    suspend fun findMovieById(id: Int): com.oscar.domain.Movie
}