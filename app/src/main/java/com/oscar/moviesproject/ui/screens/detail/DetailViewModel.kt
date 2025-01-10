package com.oscar.moviesproject.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.moviesproject.Result
import com.oscar.moviesproject.ifSuccess
import com.oscar.moviesproject.stateAsResultIn
import com.oscar.usecases.FindMovieByIdUseCase
import com.oscar.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface DetailAction{
    data object FavoriteClick : DetailAction
}

class DetailViewModel(
    id: Int,
    private val toggleFavoriteUseCase: com.oscar.usecases.ToggleFavoriteUseCase,
    private val findMovieByIdUseCase: com.oscar.usecases.FindMovieByIdUseCase
): ViewModel() {

    val state: StateFlow<Result<com.oscar.domain.Movie>> = findMovieByIdUseCase(id)
        .stateAsResultIn(viewModelScope)

    fun onAction(action: DetailAction){
        when(action){
            DetailAction.FavoriteClick -> {
                state.value.ifSuccess{ movie->
                    movie.let {
                        viewModelScope.launch { toggleFavoriteUseCase(it) }
                    }
                }
            }
            else -> {}
        }
    }
}