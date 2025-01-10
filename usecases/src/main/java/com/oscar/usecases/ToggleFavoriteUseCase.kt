package com.oscar.usecases

import com.oscar.data.MoviesRepository
import com.oscar.domain.Movie

class ToggleFavoriteUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movie: Movie) = moviesRepository.toggleFavorite(movie)

}