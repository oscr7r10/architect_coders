package com.oscar.data.datasource

import com.oscar.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<com.oscar.domain.Movie>>
    fun findMoviesById(id: Int): Flow<com.oscar.domain.Movie?>
    suspend fun isEmpty(): Boolean
    suspend fun saveMovies(movies: List<com.oscar.domain.Movie>)
}