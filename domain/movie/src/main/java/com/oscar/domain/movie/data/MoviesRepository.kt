package com.oscar.domain.movie.data

import com.oscar.domain.movie.entities.Movie
import com.oscar.domain.region.data.RegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource,
    private val regionRepository: RegionRepository
) {

    val movies: Flow<List<Movie>> = localDataSource.movies.onEach { localMovies->
        if (localMovies.isEmpty()){
            val region = regionRepository.findLastRegion()
            val remoteMovies = remoteDataSource.fetchPopularMovies(region)
            localDataSource.saveMovies(remoteMovies)
        }
    }

    fun findMovieById(id: Int): Flow<Movie> = localDataSource.findMoviesById(id)
        .onEach {
            if (it == null) {
                val remoteMovie = remoteDataSource.findMovieById(id)
                localDataSource.saveMovies(listOf(remoteMovie))
            }
        }.filterNotNull()

    suspend fun toggleFavorite(movie: Movie) {
        localDataSource.saveMovies(listOf(movie.copy(favorite = !movie.favorite)))
    }
}
