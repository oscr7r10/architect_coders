package com.oscar.movie.usecases

import com.oscar.movie.data.MoviesRepository
import com.oscar.movie.entities.Movie


class ToggleFavoriteUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movie: Movie) = moviesRepository.toggleFavorite(movie)

}