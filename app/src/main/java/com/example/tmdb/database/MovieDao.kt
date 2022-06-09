package com.example.tmdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao{

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<DbMovie>>

    @Query("SELECT movie_id, title, poster_path, overview, favorite FROM movie")
    fun getFavouriteMovies(): MutableList<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: DbMovie)

    @Query("DELETE FROM movie WHERE movie_id = :movieId")
    fun delete(movieId: Int)

}