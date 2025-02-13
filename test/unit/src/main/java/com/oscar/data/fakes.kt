package com.oscar.data

import com.oscar.domain.movie.data.MoviesLocalDataSource
import com.oscar.domain.movie.data.MoviesRemoteDataSource
import com.oscar.domain.movie.data.MoviesRepository
import com.oscar.domain.movie.entities.Movie
import com.oscar.domain.region.data.RegionDataSource
import com.oscar.domain.region.data.RegionRepository
import com.oscar.domain.region.entities.Location
import com.oscar.test.unit.sampleMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildMoviesRepositoryWith(
    localData: List<Movie> = emptyList(),
    remoteData: List<Movie> = emptyList()
): MoviesRepository {
    val regionRepository = RegionRepository(FakeRegionDataSource())
    val localDataSource = FakeLocalDataSource().apply { inMemoryMovies.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { movies = remoteData }

    return MoviesRepository(
        regionRepository = regionRepository,
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource
    )
}

class FakeRegionDataSource : RegionDataSource {

    val region : String = "US"

    override suspend fun findLastRegion(): String = region

    override suspend fun Location.toRegion(): String {
        TODO("Not yet implemented")
    }
}

class FakeLocalDataSource : MoviesLocalDataSource{

    val inMemoryMovies = MutableStateFlow<List<Movie>>(emptyList())

    override val movies: Flow<List<Movie>> = inMemoryMovies

    override fun findMoviesById(id: Int): Flow<Movie?> =
        inMemoryMovies.map { it.firstOrNull {movie -> movie.id == id } }

    override suspend fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        inMemoryMovies.value = movies
    }

}

class FakeRemoteDataSource: MoviesRemoteDataSource {

    var movies = sampleMovies(1,2,3,4)

    override suspend fun fetchPopularMovies(region: String): List<Movie> = movies

    override suspend fun findMovieById(id: Int): Movie = movies.first(){ it.id == id }
}