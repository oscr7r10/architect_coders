package com.oscar.domain.movie.usecases

import com.oscar.domain.movie.data.MoviesRepository
import com.oscar.domain.movie.entities.Movie
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movie: Movie) = moviesRepository.toggleFavorite(movie)

}