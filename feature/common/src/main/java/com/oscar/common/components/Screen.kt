package com.oscar.common.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oscar.common.theme.MoviesProjectTheme

@Composable
fun Screen(content: @Composable ()-> Unit) {
    com.oscar.common.theme.MoviesProjectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}