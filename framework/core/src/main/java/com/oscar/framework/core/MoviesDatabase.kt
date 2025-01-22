package com.oscar.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oscar.framework.movie.database.DbMovie
import com.oscar.framework.movie.database.MoviesDao

@Database(
    entities = [DbMovie::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}