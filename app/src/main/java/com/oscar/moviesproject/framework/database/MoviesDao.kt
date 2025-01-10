package com.oscar.moviesproject.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM DbMovie")
    fun fetchPopularMovies(): Flow<List<DbMovie>>

    @Query("SELECT * FROM DbMovie WHERE id = :id")
    fun findMovieById(id: Int): Flow<DbMovie?>

    @Query("SELECT COUNT(*) FROM DbMovie")
    suspend fun countMovies(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<DbMovie>)

}