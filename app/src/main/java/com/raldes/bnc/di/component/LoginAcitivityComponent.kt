package com.raldes.bnc.di.component

import com.raldes.bnc.di.annotation.scope.PerActivity
import com.raldes.bnc.di.module.LoginActivityModule
import com.raldes.bnc.di.viewmodel.LoginViewModelModule
import com.raldes.bnc.ui.activity.LoginActivity
import dagger.Component


@PerActivity
@Component(modules = [LoginActivityModule::class, LoginViewModelModule::class], dependencies = [ApplicationComponent::class])
interface LoginAcitivityComponent {

    fun inject(loginActivity: LoginActivity)
}