package com.raldes.bnc.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val dispoable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        dispoable.dispose()
    }
}