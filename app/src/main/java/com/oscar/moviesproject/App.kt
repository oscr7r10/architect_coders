package com.oscar.moviesproject

import android.app.Application
import androidx.room.Room

class App : Application() {

    lateinit var db: com.oscar.core.MoviesDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, com.oscar.core.MoviesDatabase::class.java, "movies-db")
            .build()

    }

}