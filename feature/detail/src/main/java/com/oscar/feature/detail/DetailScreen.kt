package com.oscar.feature.detail

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.oscar.feature.common.ifSuccess
import com.oscar.feature.common.ACScaffold
import com.oscar.common.R
import com.oscar.domain.movie.entities.Movie
import com.oscar.domain.movie.usecases.ToggleFavoriteUseCase
import com.oscar.feature.common.Result
import com.oscar.feature.common.components.DetailTopBar
import com.oscar.feature.common.components.MovieDetail
import com.oscar.feature.common.components.Screen

@Composable
fun DetailScreen(
    vm: DetailViewModel = hiltViewModel(),
    onBack: ()-> Unit
) {
    val state by vm.state.collectAsState()

    DetailScreen(
        state = state,
        onBack = onBack,
        onFavoriteClicked = { action ->
            vm.onAction(action)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: Result<Movie>,
    onBack: ()-> Unit,
    onFavoriteClicked: (DetailAction) -> Unit
) {

    val detailState = rememberDetailState()

    Screen {
        ACScaffold(
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
                var favorite = false
                state.ifSuccess {
                    favorite = it.favorite
                }
                FloatingActionButton(onClick = { onFavoriteClicked(DetailAction.FavoriteClick) }) {
                    Icon(
                        imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            },
            snackBarHost = {
                SnackbarHost(hostState = detailState.snackBarHostState)
            },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ) { padding, movie ->
            MovieDetail(padding = padding, movie = movie)
        }
    }
}