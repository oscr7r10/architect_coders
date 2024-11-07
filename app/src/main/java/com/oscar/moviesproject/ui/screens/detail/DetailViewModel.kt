package com.oscar.moviesproject.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.MoviesRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int): ViewModel() {

    private val repository = MoviesRepository()

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                loading = false,
                movie = repository.findMovieById(id)
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )

}