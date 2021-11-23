package com.raldes.bnc.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raldes.bnc.di.annotation.scope.ViewModelKey
import com.raldes.bnc.utils.ViewModelFactory
import com.raldes.bnc.viewmodel.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel
}