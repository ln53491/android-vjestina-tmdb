package com.example.tmdb.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [DbMovie::class]
)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}