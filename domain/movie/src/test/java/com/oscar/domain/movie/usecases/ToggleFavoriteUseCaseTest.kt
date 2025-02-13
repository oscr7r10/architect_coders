package com.oscar.domain.movie.usecases

import com.oscar.domain.movie.data.MoviesRepository
import com.oscar.test.unit.sampleMovie
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ToggleFavoriteUseCaseTest{
    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        val movie = sampleMovie(1)
        val repository = mock<MoviesRepository>()
        val useCase = ToggleFavoriteUseCase(repository)

        useCase(movie)

        verify(repository).toggleFavorite(movie)
    }
}