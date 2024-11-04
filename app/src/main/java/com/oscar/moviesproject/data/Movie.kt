package com.oscar.moviesproject.data

data class Movie(
    val id: Int,
    val title: String,
    val poster: String
)

data class PosterItemModel(
    val id: Int,
    val title: String,
    val image: String
)
