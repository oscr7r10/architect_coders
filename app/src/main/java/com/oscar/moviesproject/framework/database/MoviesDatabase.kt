package com.oscar.moviesproject.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbMovie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}