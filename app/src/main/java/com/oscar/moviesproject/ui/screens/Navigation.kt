package com.oscar.moviesproject.ui.screens

import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.location.LocationServices
import com.oscar.moviesproject.App
import com.oscar.data.MoviesRepository
import com.oscar.data.RegionRepository
import com.oscar.moviesproject.framework.GeocoderRegionDataSource
import com.oscar.moviesproject.framework.MoviesRoomDataSource
import com.oscar.moviesproject.framework.remote.MoviesClient
import com.oscar.moviesproject.framework.MoviesServerDataSource
import com.oscar.moviesproject.framework.PlayServicesLocationDataSource
import com.oscar.moviesproject.ui.screens.detail.DetailScreen
import com.oscar.moviesproject.ui.screens.detail.DetailViewModel
import com.oscar.moviesproject.ui.screens.home.HomeScreen
import com.oscar.moviesproject.ui.screens.home.HomeViewModel

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
    val app = LocalContext.current.applicationContext as App
    val moviesRepository = remember {
        com.oscar.data.MoviesRepository(
            regionRepository = com.oscar.data.RegionRepository(
                GeocoderRegionDataSource(
                    geocoder = Geocoder(app),
                    locationDataSource = PlayServicesLocationDataSource(
                        LocationServices.getFusedLocationProviderClient(
                            app
                        )
                    )
                )
            ),
            localDataSource = MoviesRoomDataSource(app.db.moviesDao()),
            remoteDataSource = MoviesServerDataSource(MoviesClient.instance)
        )
    }
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen(
                onClick = { movie ->
                    navController.navigate(
                        route = NavScreen.Detail.createRoute(movieId = movie.id)
                    )
                },
                viewModel { HomeViewModel(com.oscar.usecases.FetchMoviesUseCase(moviesRepository)) }
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
                vm = viewModel {
                    DetailViewModel(
                        movieId,
                        com.oscar.usecases.ToggleFavoriteUseCase(moviesRepository),
                        com.oscar.usecases.FindMovieByIdUseCase(moviesRepository)
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}