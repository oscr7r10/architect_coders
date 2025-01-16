package com.oscar.movie.data

import com.oscar.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<Movie>>
    fun findMoviesById(id: Int): Flow<Movie?>
    suspend fun isEmpty(): Boolean
    suspend fun saveMovies(movies: List<Movie>)
}