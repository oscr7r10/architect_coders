package com.oscar.moviesproject.data

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
    val voteAverage: Double
)

data class PosterItemModel(
    val id: Int,
    val title: String,
    val image: String
)
