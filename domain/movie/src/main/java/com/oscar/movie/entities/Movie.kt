package com.oscar.movie.entities


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val backdrop: String?,
    val originalTitle: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
    val favorite: Boolean
)

data class PosterItemModel(
    val id: Int,
    val title: String,
    val image: String
)
