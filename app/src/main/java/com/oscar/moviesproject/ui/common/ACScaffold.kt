package com.oscar.moviesproject.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.oscar.moviesproject.Result
import com.oscar.moviesproject.ui.components.LoadingProgress

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

}