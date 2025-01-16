package com.oscar.movie.data

import com.oscar.movie.entities.Movie


interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun findMovieById(id: Int): Movie
}