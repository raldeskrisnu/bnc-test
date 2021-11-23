package com.raldes.bnc.service.repository

import com.raldes.bnc.model.DetailMovie
import com.raldes.bnc.model.ListMovies
import com.raldes.bnc.service.APIService
import io.reactivex.Observable

class MovieRepositoryService(val service: APIService) {

    fun getMovieList():Observable<ListMovies> {
        return service.getListMovie()
    }

    fun getMovieDetail(id: Int):Observable<DetailMovie> {
        return service.getMovieDetail(id)
    }
}