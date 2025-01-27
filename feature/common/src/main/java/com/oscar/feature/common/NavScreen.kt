package com.oscar.feature.common

sealed class NavScreen(val route: String) {
    data object Home : NavScreen(route = "home")
    data object Detail : NavScreen(route = "detail/{${NavArgs.MovieId.key}}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}

enum class NavArgs(val key: String) {
    MovieId(key = "movieId")
}