package com.oscar.moviesproject.data.datasource

import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.datasource.database.MoviesDao

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

    val movies = moviesDao.fetchPopularMovies()

    fun findMoviesById(id: Int) = moviesDao.findMovieById(id)

    suspend fun isEmpty() = moviesDao.countMovies() == 0

    suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(movies)

}