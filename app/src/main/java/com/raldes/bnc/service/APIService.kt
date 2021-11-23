package com.raldes.bnc.service

import com.raldes.bnc.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("/movies")
    fun getListMovie():Observable<ListMovies>

    @POST("/login")
    fun login(@Body payload: User?):Observable<LoginResponse>

    @GET("movies/{id}")
    fun getMovieDetail(@Path("id") id:Int):Observable<DetailMovie>
}