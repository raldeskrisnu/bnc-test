package com.raldes.bnc.di.component

import com.raldes.bnc.di.module.ApplicationModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun initRetrofit(): Retrofit
}