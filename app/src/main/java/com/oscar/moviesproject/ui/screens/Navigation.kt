package com.oscar.moviesproject.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oscar.moviesproject.ui.screens.detail.DetailScreen
import com.oscar.moviesproject.ui.screens.detail.DetailViewModel
import com.oscar.moviesproject.ui.screens.home.HomeScreen

sealed class NavScreen(val route: String){
    data object Home: NavScreen(route = "home")
    data object Detail: NavScreen(route = "detail/{${NavArgs.MovieId.key}}"){
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}

enum class NavArgs(val key: String){
    MovieId(key = "movieId")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.Home.route){
        composable(NavScreen.Home.route){
            HomeScreen(
                onClick = {movie ->
                navController.navigate(
                    route = NavScreen.Detail.createRoute(movieId = movie.id)
                )
            })
        }
        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.MovieId.key){
                type = NavType.IntType
            })
        ) { backStackEntry->
            val movieId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.MovieId.key))
            DetailScreen(
                vm = viewModel { DetailViewModel(movieId) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}