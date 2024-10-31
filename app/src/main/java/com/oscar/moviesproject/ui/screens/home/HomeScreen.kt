package com.oscar.moviesproject.ui.screens.home

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.oscar.moviesproject.R
import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.ui.common.PermissionRequestEffect
import com.oscar.moviesproject.ui.common.getRegion
import com.oscar.moviesproject.ui.components.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (Movie)-> Unit,
    vm: HomeViewModel = viewModel()
) {

    val ctx = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()
    val state = vm.state

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {granted ->
        coroutineScope.launch {
            val region = if (granted) ctx.getRegion() else "US"
            vm.onUiReady(region)
        }
    }

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold (
            topBar = {
                TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets =  WindowInsets.safeDrawing
        ){ padding->

            if (state.loading){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            LazyVerticalGridAC(
                state = state,
                contentPadding = padding,
                onClick = {  movie ->
                    onClick(movie)
                }
            )
        }
    }
}

@Composable
fun LazyVerticalGridAC(state: HomeViewModel.UiState, contentPadding: PaddingValues, onClick: (Movie)-> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(4.dp),
        contentPadding = contentPadding
    ) {
        items(state.movies){movie->
            MovieItem(movie = movie, onClick = {onClick(movie)})
        }
    }
}

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
}