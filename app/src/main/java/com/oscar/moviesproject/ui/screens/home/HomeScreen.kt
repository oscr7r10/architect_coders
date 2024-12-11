package com.oscar.moviesproject.ui.screens.home

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.moviesproject.R
import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.PosterItemModel
import com.oscar.moviesproject.ui.components.LoadingProgress
import com.oscar.moviesproject.ui.components.PosterItem
import com.oscar.moviesproject.ui.components.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (Movie)-> Unit,
    vm: HomeViewModel = viewModel()
) {

    val homeState = rememberHomeState()
    val state = vm.state.collectAsState()

    homeState.AskRegionEffect { vm.onUiReady(it) }

    Screen {
        Scaffold (
            topBar = {
                TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                scrollBehavior = homeState.scrollBehavior
                )
            },
            modifier = Modifier
                .nestedScroll(homeState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets =  WindowInsets.safeDrawing
        ){ padding->

            if (state.value.loading){
                LoadingProgress(modifier = Modifier.fillMaxSize())
            }
            LazyVerticalGridAC(
                state = state.value,
                contentPadding = padding,
                onClick = {  movie ->
                    onClick(movie)
                }
            )
        }
    }
}

@Composable
fun LazyVerticalGridAC(
    state: HomeViewModel.UiState,
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
        items(state.movies){movie->
            //MovieItem(movie = movie, onClick = {onClick(movie)})
            PosterItem(
                posterItemModel = PosterItemModel(id = movie.id, image = movie.poster, title = movie.title),
                onClick = {onClick(movie)}
            )
        }
    }
}

/*
@Composable
fun MovieItem(movie: Movie, onClick: () ->Unit) {
    Column {
        AsyncImage(
            model = movie.poster,
            contentDescription = movie.title,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .aspectRatio(ratio = 2 / 3f)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}*/