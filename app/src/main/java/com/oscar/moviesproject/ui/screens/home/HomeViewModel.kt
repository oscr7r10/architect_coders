package com.oscar.moviesproject.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.moviesproject.Result
import com.oscar.moviesproject.data.Movie
import com.oscar.moviesproject.stateAsResultIn
import com.oscar.moviesproject.usecases.FetchMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class HomeViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase
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