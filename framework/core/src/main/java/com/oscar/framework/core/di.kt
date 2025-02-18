package com.oscar.framework.core

import android.app.Application
import androidx.room.Room
import com.oscar.framework.movie.network.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

    @Provides
    fun providesMoviesDao(database: MoviesDatabase) = database.moviesDao()

    @Provides
    @Singleton
    fun provideMoviesService(
        @Named("apiKey") apiKey: String,
        @Named("apiUrl") apiUrl: String
    ): MoviesService = MoviesClient(apiKey, apiUrl).instance

}

@Module
@InstallIn(SingletonComponent::class)
object FrameworkCoreExtrasModule{
    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        MoviesDatabase::class.java,
        "movies.db"
    ).build()

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl() = "https://api.themoviedb.org/3/"

}