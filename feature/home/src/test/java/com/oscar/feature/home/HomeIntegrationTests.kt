package com.oscar.feature.home

import app.cash.turbine.test
import com.oscar.data.buildMoviesRepositoryWith
import com.oscar.domain.movie.data.MoviesRepository
import com.oscar.domain.movie.entities.Movie
import com.oscar.domain.movie.usecases.FetchMoviesUseCase
import com.oscar.feature.common.Result
import com.oscar.test.unit.sampleMovies
import com.oscar.testrules.CoroutinesTestRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest {
        val remoteData = sampleMovies(1, 2)
        val vm = buildViewModelWith(remoteData = remoteData)

        vm.onUiReady()
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Movie>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `Data is loaded from local data source when available`() = runTest {
        val localData = sampleMovies(1,2)

        val vm = buildViewModelWith(localData = localData)

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }

    }

    private fun buildViewModelWith(localData: List<Movie> = emptyList(), remoteData: List<Movie> = emptyList()) =
        HomeViewModel(
            FetchMoviesUseCase(
                buildMoviesRepositoryWith(localData = localData, remoteData = remoteData)
            )
        )
}