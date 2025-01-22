package com.oscar.moviesproject.ui.screens

import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.location.LocationServices
import com.oscar.framework.core.MoviesClient
import com.oscar.feature.detail.DetailScreen
import com.oscar.moviesproject.App
import com.oscar.domain.movie.data.MoviesRepository
import com.oscar.framework.movie.database.MoviesRoomDataSource
import com.oscar.framework.movie.network.MoviesServerDataSource
import com.oscar.moviesproject.BuildConfig
import com.oscar.framework.region.GeocoderRegionDataSource
import com.oscar.framework.region.PlayServicesLocationDataSource
import com.oscar.domain.region.data.RegionRepository
import com.oscar.feature.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

sealed class NavScreen(val route: String) {
    data object Home : NavScreen(route = "home")
    data object Detail : NavScreen(route = "detail/{${NavArgs.MovieId.key}}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}

enum class NavArgs(val key: String) {
    MovieId(key = "movieId")
}

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
            val movieId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.MovieId.key))
            DetailScreen(
                vm = koinViewModel(parameters = { parametersOf(movieId) }),
                onBack = { navController.popBackStack() }
            )
        }
    }
}