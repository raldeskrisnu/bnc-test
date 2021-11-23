package com.raldes.bnc.service.repository

import com.raldes.bnc.model.LoginResponse
import com.raldes.bnc.model.User
import com.raldes.bnc.service.APIService
import io.reactivex.Observable
import javax.inject.Inject

class LoginRepositoryService(val service:APIService) {

    fun attemptLogin(user: User): Observable<LoginResponse> {
       return service.login(user)
    }
}