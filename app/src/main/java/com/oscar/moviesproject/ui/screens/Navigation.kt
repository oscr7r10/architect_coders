package com.oscar.moviesproject.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oscar.feature.common.NavArgs
import com.oscar.feature.common.NavScreen
import com.oscar.feature.detail.DetailScreen
import com.oscar.feature.home.HomeScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {

            HomeScreen(
                onClick = { movie ->
                    navController.navigate(
                        route = NavScreen.Detail.createRoute(movieId = movie.id)
                    )
                }
            )
        }
        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.MovieId.key) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            DetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}