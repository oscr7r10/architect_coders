package com.oscar.movie.usecases

import com.oscar.movie.data.MoviesRepository
import com.oscar.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies


}