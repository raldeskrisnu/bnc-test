package com.raldes.bnc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raldes.bnc.model.DataMovie
import com.raldes.bnc.model.DetailMovie
import com.raldes.bnc.service.repository.MovieRepositoryService
import com.raldes.bnc.utils.DisposableUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MovieViewModel @Inject constructor(
        private val movieRepositoryService: MovieRepositoryService
) : ViewModel() {

    var getMovieListDisposable: Disposable? = null

    var getMovieDetailDisposable: Disposable? = null

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>get() = _loading

    private val _listMovie = MutableLiveData<List<DataMovie>>()
    val getMovieList: LiveData<List<DataMovie>>get() = _listMovie

    private val _detailMovie = MutableLiveData<DetailMovie>()
    val getDetailMovie: LiveData<DetailMovie>get() = _detailMovie

    fun getListMovie() {
        DisposableUtils.dispose(getMovieListDisposable)
        _loading.value = true
        getMovieListDisposable = movieRepositoryService.getMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _listMovie.value = it.dataMovies
                _loading.value = false
            }, {
                Timber.d(it.message)
                _loading.value = false
            })
    }

    fun getMovieDetail(id: Int) {
        DisposableUtils.dispose(getMovieDetailDisposable)

        getMovieDetailDisposable = movieRepositoryService.getMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                _detailMovie.value = it
            }, {
                Timber.d(it.message)
            })
    }
}