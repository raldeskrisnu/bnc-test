package com.raldes.bnc.di.module

import androidx.lifecycle.ViewModel
import com.raldes.bnc.di.annotation.scope.PerActivity
import com.raldes.bnc.service.APIService
import com.raldes.bnc.service.repository.LoginRepositoryService
import com.raldes.bnc.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginActivityModule {

    @PerActivity
    @Provides
    fun provideService(loginService: APIService): LoginRepositoryService {
        return LoginRepositoryService(loginService)
    }

    @PerActivity
    @Provides
    fun provideRetrofit(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @PerActivity
    @Provides
    fun providerViewModel(loginRepositoryService: LoginRepositoryService): ViewModel {
        return LoginViewModel(loginRepositoryService)
    }

}