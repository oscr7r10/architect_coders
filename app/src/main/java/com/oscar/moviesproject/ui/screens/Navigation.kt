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
import com.oscar.core.MoviesClient
import com.oscar.detail.DetailScreen
import com.oscar.detail.DetailViewModel
import com.oscar.moviesproject.App
import com.oscar.movie.usecases.FindMovieByIdUseCase
import com.oscar.movie.usecases.ToggleFavoriteUseCase
import com.oscar.movie.usecases.FetchMoviesUseCase
import com.oscar.movie.data.MoviesRepository
import com.oscar.movie.database.MoviesRoomDataSource
import com.oscar.movie.network.MoviesServerDataSource
import com.oscar.moviesproject.BuildConfig
import com.oscar.region.GeocoderRegionDataSource
import com.oscar.region.PlayServicesLocationDataSource
import com.oscar.region.data.RegionRepository

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
        MoviesRepository(
            regionRepository = RegionRepository(
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
            remoteDataSource = MoviesServerDataSource(
                MoviesClient(
                    BuildConfig.TMDB_API_KEY
                ).instance)
        )
    }
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            com.oscar.home.HomeScreen(
                onClick = { movie ->
                    navController.navigate(
                        route = NavScreen.Detail.createRoute(movieId = movie.id)
                    )
                },
                viewModel {
                    com.oscar.home.HomeViewModel(
                        FetchMoviesUseCase(
                            moviesRepository
                        )
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
                vm = viewModel {
                    DetailViewModel(
                        movieId,
                        ToggleFavoriteUseCase(moviesRepository),
                        FindMovieByIdUseCase(moviesRepository)
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}