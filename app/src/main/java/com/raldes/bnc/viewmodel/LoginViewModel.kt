package com.raldes.bnc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raldes.bnc.model.LoginResponse
import com.raldes.bnc.model.User
import com.raldes.bnc.model.data
import com.raldes.bnc.service.repository.LoginRepositoryService
import com.raldes.bnc.utils.DisposableUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepositoryService: LoginRepositoryService
) : ViewModel() {

    var loginServiceDisposable: Disposable? = null

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    private var _successState = MutableLiveData<data>()
    val success: LiveData<data> = _successState

    fun atemmptLogin(username:String, password:String) {
        DisposableUtils.dispose(loginServiceDisposable)
        _loading.value = true

        val user = User(username, password)

        loginServiceDisposable =
            loginRepositoryService.attemptLogin(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it->
                    _loading.value = false
                    _successState.value = it.data
                }, {
                    _loading.value = false
                })
    }
}