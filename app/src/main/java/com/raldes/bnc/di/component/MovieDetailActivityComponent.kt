package com.raldes.bnc.di.component

import com.raldes.bnc.di.annotation.scope.PerActivity
import com.raldes.bnc.di.module.MovieModule
import com.raldes.bnc.di.viewmodel.MovieViewModelModule
import com.raldes.bnc.ui.activity.MovieDetailActivity
import dagger.Component

@PerActivity
@Component(modules = [MovieModule::class, MovieViewModelModule::class], dependencies = [ApplicationComponent::class])
interface MovieDetailActivityComponent {
    fun inject(movieDetailActivity: MovieDetailActivity)
}