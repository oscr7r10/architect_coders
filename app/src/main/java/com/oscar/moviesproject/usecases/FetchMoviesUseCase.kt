package com.oscar.moviesproject.usecases

import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.MoviesRepository
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies


}