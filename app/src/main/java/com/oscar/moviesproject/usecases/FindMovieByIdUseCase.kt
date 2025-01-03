package com.oscar.moviesproject.usecases

import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.MoviesRepository
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val moviesRepository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<Movie> = moviesRepository.findMovieById(id)

}