package com.oscar.usecases

import com.oscar.data.MoviesRepository
import com.oscar.domain.Movie
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val moviesRepository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<Movie> = moviesRepository.findMovieById(id)

}