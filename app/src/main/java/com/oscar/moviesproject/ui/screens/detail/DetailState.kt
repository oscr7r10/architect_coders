package com.oscar.moviesproject.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

class DetailState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackBarHostState: SnackbarHostState
) {

    @Composable
    fun ShowMessageState(message: String?, onMessageShown: ()-> Unit) {
        LaunchedEffect(message) {
            message?.let {
                snackBarHostState.currentSnackbarData?.dismiss()
                snackBarHostState.showSnackbar(it)
                onMessageShown()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
): DetailState {
    return remember(scrollBehavior, snackBarHostState) { DetailState(scrollBehavior, snackBarHostState) }
}
