package com.oscar.framework.movie.database

import com.oscar.domain.movie.data.MoviesLocalDataSource
import com.oscar.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MoviesRoomDataSource @Inject constructor(private val moviesDao: MoviesDao) :
    MoviesLocalDataSource {

    override val movies: Flow<List<Movie>> = moviesDao.fetchPopularMovies().map { movies-> movies.map { it.toDomainMovie() } }

    override fun findMoviesById(id: Int): Flow<Movie?> = moviesDao.findMovieById(id).map { it?.toDomainMovie() }

    override suspend fun isEmpty() = moviesDao.countMovies() == 0

    override suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(movies.map { it.toDbMovie() })

}

private fun Movie.toDbMovie() = DbMovie(
    id = id,
    title = title,
    overview = overview,
    poster = poster,
    backdrop = backdrop,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    favorite = favorite,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity
)

private fun DbMovie.toDomainMovie(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        poster = "https://image.tmdb.org/t/p/w185/$poster",
        backdrop = backdrop.let { "https://image.tmdb.org/t/p/w780/$it" },
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        favorite = favorite
    )