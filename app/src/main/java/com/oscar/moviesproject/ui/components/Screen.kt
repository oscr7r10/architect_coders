package com.oscar.moviesproject.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oscar.moviesproject.ui.theme.MoviesProjectTheme

@Composable
fun Screen(content: @Composable ()-> Unit) {
    MoviesProjectTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}