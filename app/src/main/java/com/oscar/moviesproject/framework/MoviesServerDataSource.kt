package com.oscar.moviesproject.framework

import com.oscar.domain.Movie
import com.oscar.data.datasource.MoviesRemoteDataSource
import com.oscar.moviesproject.framework.remote.MoviesService
import com.oscar.moviesproject.framework.remote.RemoteMovie

class MoviesServerDataSource(
    private val moviesService: MoviesService
) : com.oscar.data.datasource.MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<Movie> =
        moviesService
            .fetchPopularMovies(region)
            .results
            .map { it.toDomainModel() }

    override suspend fun findMovieById(id: Int): Movie =
        moviesService
            .fetchMovieById(id)
            .toDomainModel()
}

private fun RemoteMovie.toDomainModel(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        poster = "https://image.tmdb.org/t/p/w185/$posterPath",
        backdrop = backdropPath.let { "https://image.tmdb.org/t/p/w780/$it" },
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        popularity = popularity,
        voteAverage = voteAverage,
        favorite = false
    )