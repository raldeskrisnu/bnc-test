package com.raldes.bnc.utils

import io.reactivex.disposables.Disposable

class DisposableUtils {

    companion object {

        fun dispose(disposable : Disposable?) {
            disposable?.dispose()
        }
    }
}