package com.oscar.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.feature.common.Result
import com.oscar.feature.common.ifSuccess
import com.oscar.feature.common.stateAsResultIn
import com.oscar.domain.movie.entities.Movie
import com.oscar.domain.movie.usecases.FindMovieByIdUseCase
import com.oscar.domain.movie.usecases.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

sealed interface DetailAction{
    data object FavoriteClick : DetailAction
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("movieId") id: Int,
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