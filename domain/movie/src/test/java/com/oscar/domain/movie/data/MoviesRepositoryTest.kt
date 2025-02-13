package com.oscar.domain.movie.data

import com.oscar.domain.movie.entities.Movie
import com.oscar.domain.region.data.RegionRepository
import com.oscar.test.unit.sampleMovie
import com.oscar.test.unit.sampleMovies
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var regionRepository: RegionRepository

    @Mock
    lateinit var localDataSource: MoviesLocalDataSource

    @Mock
    lateinit var remoteDataSource: MoviesRemoteDataSource

    private lateinit var repository: MoviesRepository

    @Before
    fun setUp() {
        repository = MoviesRepository(
            regionRepository = regionRepository,
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    //Add test cases here
    @Test
    fun `Popular movies are taken local data source if available`(): Unit = runBlocking {
        val localMovies = sampleMovies(1,2)
        whenever(localDataSource.movies).thenReturn(flowOf(localMovies))

        val result = repository.movies

        assertEquals(localMovies, result.first())
    }

    @Test
    fun `Popular movies are saved to local data source when it's empty`(): Unit = runBlocking {
        val localMovies = emptyList<Movie>()
        val remoteMovies = sampleMovies(1,2)
        whenever(localDataSource.movies).thenReturn(flowOf(localMovies))
        whenever(regionRepository.findLastRegion()).thenReturn("US")
        whenever(remoteDataSource.fetchPopularMovies("US")).thenReturn(remoteMovies)

        repository.movies.first()

        verify(localDataSource).saveMovies(remoteMovies)
    }

    @Test
    fun `Toggling favorite update local data source`(): Unit = runBlocking {
        val movie = sampleMovie(3)

        repository.toggleFavorite(movie)

        verify(localDataSource).saveMovies( argThat{
            get(0).id == 3 }
        )
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite movie`(): Unit = runBlocking {
        val movie = sampleMovie(1).copy(favorite = false)
        repository.toggleFavorite(movie)

        verify(localDataSource).saveMovies(argThat{
            get(0).favorite
        })
    }

    @Test
    fun `Switching favorite marks as unfavorite a favorite movie`(): Unit = runBlocking {
        val movie = sampleMovie(1).copy(favorite = true)
        repository.toggleFavorite(movie)

        verify(localDataSource).saveMovies(argThat{
            !get(0).favorite
        })
    }
}