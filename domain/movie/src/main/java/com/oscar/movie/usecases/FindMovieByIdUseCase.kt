package com.oscar.movie.usecases

import com.oscar.movie.data.MoviesRepository
import com.oscar.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val moviesRepository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<Movie> = moviesRepository.findMovieById(id)

}