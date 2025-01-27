package com.oscar.framework.movie

import com.oscar.domain.movie.data.MoviesLocalDataSource
import com.oscar.domain.movie.data.MoviesRemoteDataSource
import com.oscar.framework.movie.database.MoviesRoomDataSource
import com.oscar.framework.movie.network.MoviesServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkMovieModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSource: MoviesRoomDataSource): MoviesLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: MoviesServerDataSource): MoviesRemoteDataSource
}

