package com.raldes.bnc.model

import com.google.gson.annotations.SerializedName

data class DetailMovie(

    @SerializedName("data")
    val data: DataDetailMovie
)

data class DataDetailMovie(

    @SerializedName("id")
    val id:Int,

    @SerializedName("title")
    val title:String,

    @SerializedName("year")
    val year:Int,

    @SerializedName("rating")
    val rating:Int,

    @SerializedName("imageUrl")
    val imageUrl:String,

    @SerializedName("imageLargeUrl")
    val imageLarge:String,

    @SerializedName("starring")
    val starring:List<String>,

    @SerializedName("desc")
    val desc:String,

    @SerializedName("releasedate")
    val date:String,

    @SerializedName("duration")
    val duration:String,

    @SerializedName("genre")
    val genre:String

)