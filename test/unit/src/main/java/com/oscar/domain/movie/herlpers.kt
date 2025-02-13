package com.oscar.test.unit

import com.oscar.domain.movie.entities.Movie

fun sampleMovie(id: Int) = Movie(
    id = id,
    title = "Title",
    overview = "Overview",
    releaseDate = "01/01/2025",
    poster = "",
    backdrop = "",
    originalTitle = "EN",
    originalLanguage = "Title",
    popularity = 5.0,
    voteAverage = 5.1,
    favorite = false
)

fun sampleMovies(vararg ids: Int) = ids.map { sampleMovie(it) }