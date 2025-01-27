package com.oscar.domain.movie.usecases

import com.oscar.domain.movie.data.MoviesRepository
import com.oscar.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies


}