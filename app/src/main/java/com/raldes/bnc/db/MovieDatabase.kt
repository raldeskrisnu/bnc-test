package com.raldes.bnc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raldes.bnc.db.dao.DataMovieDao
import com.raldes.bnc.model.DataMovie

@Database(entities = [DataMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun dataMovieDao(): DataMovieDao
}