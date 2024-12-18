package com.oscar.moviesproject.ui.screens.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.oscar.moviesproject.R
import com.oscar.moviesproject.ui.components.DetailTopBar
import com.oscar.moviesproject.ui.components.MovieDetail
import com.oscar.moviesproject.ui.components.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: ()-> Unit) {
    val state by vm.state.collectAsState()
    val detailState = rememberDetailState()

    detailState.ShowMessageState(message = state.message) {
        vm.onAction(DetailAction.MessageShown)
    }

    Screen {
        Scaffold (
            topBar = {
                DetailTopBar(
                    title = state.movie?.title.orEmpty(),
                    scrollBehavior = detailState.scrollBehavior,
                    onBack = onBack
                )
            },
            floatingActionButton = {
                                   FloatingActionButton(onClick = { vm.onAction(DetailAction.FavoriteClick)}) {
                                       Icon(
                                           imageVector = Icons.Default.FavoriteBorder,
                                           contentDescription = stringResource(id = R.string.back)
                                       )
                                   }
            },
            snackbarHost = {
                           SnackbarHost(hostState = detailState.snackBarHostState)
            },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ){ padding ->
            state.movie?.let { movie->
                MovieDetail(padding = padding, movie = movie)
            }
        }
    }
}