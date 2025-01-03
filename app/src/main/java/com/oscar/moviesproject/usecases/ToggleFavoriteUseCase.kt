package com.oscar.moviesproject.usecases

import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.MoviesRepository

class ToggleFavoriteUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movie: Movie) = moviesRepository.toggleFavorite(movie)

}