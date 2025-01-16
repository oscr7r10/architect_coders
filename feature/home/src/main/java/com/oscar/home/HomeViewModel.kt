package com.oscar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.common.Result
import com.oscar.movie.entities.Movie
import com.oscar.common.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class HomeViewModel(
    private val fetchMoviesUseCase: com.oscar.movie.usecases.FetchMoviesUseCase
) : ViewModel() {


    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<Movie>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchMoviesUseCase.invoke() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady(){
        uiReady.value = true
    }


}