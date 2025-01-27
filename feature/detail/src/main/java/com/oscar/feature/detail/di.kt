package com.oscar.feature.detail

import androidx.lifecycle.SavedStateHandle
import com.oscar.feature.common.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)//Solo sobreviva durante el vm
class DetailViewModelModule{

    @Provides
    @ViewModelScoped
    @Named("movieId")
    fun provideMovieId(savedStateHandle: SavedStateHandle): Int {
        return savedStateHandle[NavArgs.MovieId.key]
            ?: throw IllegalArgumentException("Movie not found")
    }
}