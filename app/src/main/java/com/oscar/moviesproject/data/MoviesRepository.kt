package com.oscar.moviesproject.data

import com.oscar.moviesproject.data.datasource.MoviesLocalDataSource
import com.oscar.moviesproject.data.datasource.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

class MoviesRepository(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource,
    private val regionRepository: RegionRepository
) {

    val movies: Flow<List<Movie>> = localDataSource.movies.onEach {localMovies->
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
