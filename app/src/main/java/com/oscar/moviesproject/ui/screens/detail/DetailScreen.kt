package com.oscar.moviesproject.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oscar.moviesproject.R
import com.oscar.moviesproject.ui.components.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: ()-> Unit) {
    val state = vm.state

    Screen {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(text = state.movie?.title ?: "") },
                    navigationIcon = {
                       IconButton(onClick = { onBack }) {
                           Icon(
                               imageVector = Icons.Default.ArrowBack,
                               contentDescription = stringResource(id = R.string.back)
                           )
                       }
                    }
                )
            }
        ){padding ->
            Column (
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ){
                state.movie?.let { movie ->
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )
                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}