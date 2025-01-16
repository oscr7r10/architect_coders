package com.oscar.movie.network

import com.oscar.movie.data.MoviesRemoteDataSource
import com.oscar.movie.entities.Movie

class MoviesServerDataSource(
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {

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