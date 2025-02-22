package com.oscar.moviesproject.ui.screens.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.oscar.moviesproject.R
import com.oscar.moviesproject.ifSuccess
import com.oscar.moviesproject.ui.common.ACScaffold
import com.oscar.moviesproject.ui.components.DetailTopBar
import com.oscar.moviesproject.ui.components.MovieDetail
import com.oscar.moviesproject.ui.components.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: ()-> Unit) {
    val state by vm.state.collectAsState()
    val detailState = rememberDetailState()

    Screen {
        ACScaffold (
            state = state,
            topBar = {
                var title = ""
                state.ifSuccess { title = it.title }
                DetailTopBar(
                    title = title,
                    scrollBehavior = detailState.scrollBehavior,
                    onBack = onBack
                )
            },
            floatingActionButton = {
                var favorite: Boolean = false
                state.ifSuccess {
                    favorite = it.favorite
                }
                FloatingActionButton(onClick = { vm.onAction(DetailAction.FavoriteClick) }) {
                    Icon(
                        imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            },
            snackBarHost =  {
                           SnackbarHost(hostState = detailState.snackBarHostState)
            },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ){ padding, movie ->
            MovieDetail(padding = padding, movie = movie)
        }
    }
}