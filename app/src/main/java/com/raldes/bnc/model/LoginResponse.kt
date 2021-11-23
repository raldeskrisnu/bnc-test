package com.raldes.bnc.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("data")
    val data: data
)

data class data (

    @SerializedName("token")
    val token:String
)