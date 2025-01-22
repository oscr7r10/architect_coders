package com.oscar.domain.movie.data

import com.oscar.domain.movie.entities.Movie


interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun findMovieById(id: Int): Movie
}