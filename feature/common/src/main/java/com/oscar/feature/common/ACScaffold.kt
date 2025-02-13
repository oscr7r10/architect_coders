package com.oscar.feature.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.oscar.feature.common.components.LoadingProgress

@Composable
fun <T> ACScaffold(
    state: Result<T>,
    modifier: Modifier = Modifier,
    topBar: @Composable ()-> Unit = {},
    bottomBar: @Composable ()-> Unit = {},
    snackBarHost: @Composable ()-> Unit = {},
    floatingActionButton: @Composable ()-> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(backgroundColor = containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues, T) -> Unit
) {
   Scaffold (
       modifier = modifier,
       topBar = topBar,
       bottomBar = bottomBar,
       snackbarHost = snackBarHost,
       floatingActionButton = floatingActionButton,
       floatingActionButtonPosition = floatingActionButtonPosition,
       containerColor = containerColor,
       contentColor = contentColor,
       contentWindowInsets = contentWindowInsets
   ){ padding ->
       when(state){
           is Result.Error -> {
               ErrorText(
                   error = state.throwable,
                   modifier = Modifier.padding(padding)
               )
           }
           Result.Loading -> {
               LoadingProgress(modifier = Modifier.padding(padding))
           }
           is Result.Success -> {
               content(padding, state.data)
           }
       }
   }
}

@Composable
fun ErrorText(error: Throwable, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.error) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Error",
                modifier = Modifier.size(72.dp)
            )
            Text(
                text = error.localizedMessage ?: "An error occurred",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}