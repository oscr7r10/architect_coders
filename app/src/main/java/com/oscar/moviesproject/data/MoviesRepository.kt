package com.oscar.moviesproject.data

class MoviesRepository {

    suspend fun fetchPopularMovies(region: String): List<Movie> =
        MoviesClient
            .instance
            .fetchPopularMovies(region)
            .results
            .map { it.toDomainModel() }

    suspend fun findMovieById(id: Int): Movie =
        MoviesClient
            .instance
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
        voteAverage = voteAverage
    )