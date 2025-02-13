package com.oscar.feature.detail

import app.cash.turbine.test
import com.oscar.data.buildMoviesRepositoryWith
import com.oscar.domain.movie.usecases.FindMovieByIdUseCase
import com.oscar.domain.movie.usecases.ToggleFavoriteUseCase
import com.oscar.feature.common.Result
import com.oscar.test.unit.sampleMovie
import com.oscar.test.unit.sampleMovies
import com.oscar.testrules.CoroutinesTestRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailViewModel

    @Before
    fun setUp() {
        val moviesRepository = buildMoviesRepositoryWith(localData = sampleMovies(1,2))

        vm = DetailViewModel(
            2,
            findMovieByIdUseCase = FindMovieByIdUseCase(moviesRepository),
            toggleFavoriteUseCase = ToggleFavoriteUseCase(moviesRepository)
        )
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest{
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleMovies(2)), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Favorite is updated in local data source`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleMovie(2)), awaitItem())

            vm.onAction(DetailAction.FavoriteClick)
            //Las corrutinas se ejecutan con runCurrent() o advanceUntilIdle() o advanceTimeBy()
            runCurrent()

            assertEquals(Result.Success(sampleMovie(2).copy(favorite = true)), awaitItem())
        }
    }
}