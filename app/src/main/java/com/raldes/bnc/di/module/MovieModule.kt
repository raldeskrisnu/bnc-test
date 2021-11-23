package com.raldes.bnc.di.module

import androidx.lifecycle.ViewModel
import com.raldes.bnc.di.annotation.scope.PerActivity
import com.raldes.bnc.service.repository.MovieRepositoryService
import com.raldes.bnc.service.APIService
import com.raldes.bnc.viewmodel.MovieViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieModule {

    @PerActivity
    @Provides
    fun provideService(movieService: APIService): MovieRepositoryService {
        return MovieRepositoryService(movieService)
    }

    @PerActivity
    @Provides
    fun provideRetrofit(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @PerActivity
    @Provides
    fun providerViewModel(movieRepositoryService: MovieRepositoryService):ViewModel {
        return MovieViewModel(movieRepositoryService)
    }

}