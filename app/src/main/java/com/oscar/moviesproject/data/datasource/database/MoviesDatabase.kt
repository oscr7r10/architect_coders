package com.oscar.moviesproject.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oscar.moviesproject.data.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}