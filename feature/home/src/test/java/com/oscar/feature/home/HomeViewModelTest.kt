package com.oscar.feature.home

import app.cash.turbine.test
import com.oscar.domain.movie.usecases.FetchMoviesUseCase
import com.oscar.feature.common.Result
import com.oscar.test.unit.sampleMovie
import com.oscar.test.unit.sampleMovies
import com.oscar.testrules.CoroutinesTestRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    private lateinit var vm : HomeViewModel

    @Before
    fun setUp(){
        vm = HomeViewModel(fetchMoviesUseCase)
    }

    @Test
    fun `Movies are not required if UI is not ready`() = runTest {
        vm.state.first()
        //Ejecutar todas las corrutinas que se han ejecutado en el proceso actual
        runCurrent()

        verify(fetchMoviesUseCase, times(0)).invoke()
    }

    @Test
    fun `Movies are requested if UI is ready`()  = runTest {
        val movies = sampleMovies(1, 2, 3)
        whenever(fetchMoviesUseCase()).thenReturn(flowOf(movies))

        vm.onUiReady()

        vm.state.test{
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(movies), awaitItem())
        }
    }

    @Test
    fun `Error is propagated when request fails`() = runTest{
        val error = RuntimeException("Boom")
        whenever(fetchMoviesUseCase()).thenThrow(error)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exceptionMessage = (awaitItem() as Result.Error).throwable.message
            assertEquals("Boom", exceptionMessage)
        }
    }
}