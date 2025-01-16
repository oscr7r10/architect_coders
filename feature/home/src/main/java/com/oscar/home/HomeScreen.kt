package com.oscar.home

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.oscar.common.ACScaffold
import com.oscar.common.PermissionRequestEffect
import com.oscar.common.components.Screen
import com.oscar.movie.entities.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (com.oscar.movie.entities.Movie)-> Unit,
    vm: HomeViewModel = viewModel()
) {

    val homeState = rememberHomeState()
    val state by vm.state.collectAsState()

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
        vm.onUiReady()
    }

    Screen {
        ACScaffold(
            state = state,
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    scrollBehavior = homeState.scrollBehavior
                )
            },
            modifier = Modifier
                .nestedScroll(homeState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { padding, movies ->

            /*if (state.value.loading){
                LoadingProgress(modifier = Modifier.fillMaxSize())
            }*/
            LazyVerticalGridAC(
                movies = movies,
                contentPadding = padding,
                onClick = { movie ->
                    onClick(movie)
                }
            )
        }
    }
}

@Composable
fun LazyVerticalGridAC(
    movies: List<Movie>,
    contentPadding: PaddingValues,
    onClick: (Movie)-> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(4.dp),
        contentPadding = contentPadding
    ) {
        items(movies){movie->
            MovieItem(movie = movie, onClick = {onClick(movie)})
        }
    }
}


@Composable
fun MovieItem(movie: Movie, onClick: () ->Unit) {
    Column {
        Box {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .fillMaxWidth()
                    .aspectRatio(ratio = 2 / 3f)
                    .clip(MaterialTheme.shapes.small)
            )
            if (movie.favorite){
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = com.oscar.common.R.string.back),
                    tint = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.padding(8.dp)
                        .align(Alignment.TopEnd)
                )
            }
        }
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}