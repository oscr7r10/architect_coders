package com.oscar.moviesproject.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.data.MoviesRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface DetailAction{
    data object FavoriteClick : DetailAction
    data object MessageShown : DetailAction
}

class DetailViewModel(private val id: Int): ViewModel() {

    private val repository = MoviesRepository()

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(
                loading = false,
                movie = repository.findMovieById(id)
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val message: String? = null
    )

    fun onAction(action: DetailAction){
        when(action){
            DetailAction.FavoriteClick -> _state.update { it.copy(message = "Favorite clicked") }
            DetailAction.MessageShown -> _state.update { it.copy(message = null) }
        }
    }

}