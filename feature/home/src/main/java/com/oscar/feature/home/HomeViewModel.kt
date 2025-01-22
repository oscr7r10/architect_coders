package com.oscar.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.feature.common.Result
import com.oscar.domain.movie.entities.Movie
import com.oscar.feature.common.stateAsResultIn
import com.oscar.domain.movie.usecases.FetchMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import org.koin.android.annotation.KoinViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@KoinViewModel
class HomeViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {


    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<Result<List<Movie>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchMoviesUseCase.invoke() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady(){
        uiReady.value = true
    }


}