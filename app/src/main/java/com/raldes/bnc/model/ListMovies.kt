package com.raldes.bnc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListMovies(

    @SerializedName("data")
    var dataMovies: List<DataMovie>
)

@Entity(tableName = "dataMovie")
data class DataMovie(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id:Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title:String,

    @ColumnInfo(name = "year")
    @SerializedName("year")
    val year:Int,

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    val rating:Int,

    @ColumnInfo(name = "imageUrl")
    @SerializedName("imageUrl")
    val image: String

): Serializable
