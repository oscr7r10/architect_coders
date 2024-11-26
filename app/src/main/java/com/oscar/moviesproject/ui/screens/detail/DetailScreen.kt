package com.oscar.moviesproject.ui.screens.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.oscar.moviesproject.ui.components.DetailTopBar
import com.oscar.moviesproject.ui.components.MovieDetail
import com.oscar.moviesproject.ui.components.Screen

@Composable
fun DetailScreen(vm: DetailViewModel, onBack: ()-> Unit) {
    val state = vm.state.collectAsState()

    Screen {
        Scaffold (
            topBar = {
                DetailTopBar(title = state.value.movie?.title.orEmpty(), onBack)
            }
        ){ padding ->
            state.value.movie?.let { movie->
                MovieDetail(padding = padding, movie = movie)
            }
        }
    }
}