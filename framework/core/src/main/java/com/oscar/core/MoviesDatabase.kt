package com.oscar.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oscar.movie.database.MoviesDao

@Database(
    entities = [com.oscar.movie.database.DbMovie::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}