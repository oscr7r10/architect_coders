package com.oscar.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.common.Result
import com.oscar.common.ifSuccess
import com.oscar.common.stateAsResultIn
import com.oscar.movie.entities.Movie
import com.oscar.movie.usecases.FindMovieByIdUseCase
import com.oscar.movie.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface DetailAction{
    data object FavoriteClick : DetailAction
}

class DetailViewModel(
    id: Int,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val findMovieByIdUseCase: FindMovieByIdUseCase
): ViewModel() {

    val state: StateFlow<Result<Movie>> = findMovieByIdUseCase(id)
        .stateAsResultIn(viewModelScope)

    fun onAction(action: DetailAction){
        when(action){
            DetailAction.FavoriteClick -> {
                state.value.ifSuccess{ movie->
                    viewModelScope.launch {
                        toggleFavoriteUseCase(movie)
                    }
                }
            }
            else -> {}
        }
    }
}