package com.oscar.moviesproject.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val repository = MoviesRepository()

    fun onUiReady(region: String){
        viewModelScope.launch {
            state = UiState(loading = true)
            delay(2000)
            state = UiState(
                loading = true,
                movies = repository.fetchPopularMovies(region))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )

}