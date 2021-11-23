package com.raldes.bnc.db.dao

import androidx.room.*
import com.raldes.bnc.model.DataMovie


@Dao
interface DataMovieDao {

    @Query("SELECT * FROM dataMovie")
    fun getAll(): List<DataMovie?>?

    @Insert
    fun insert(dataMovie: DataMovie)

    @Delete
    fun delete(dataMovie: DataMovie)

    @Update
    fun update(dataMovie: DataMovie)
}