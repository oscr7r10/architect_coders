package com.oscar.usecases

import com.oscar.data.MoviesRepository
import com.oscar.domain.Movie
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies


}