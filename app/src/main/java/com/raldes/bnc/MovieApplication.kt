package com.raldes.bnc

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.raldes.bnc.di.module.ApplicationModule
import com.raldes.bnc.di.component.ApplicationComponent
import com.raldes.bnc.di.component.DaggerApplicationComponent

class MovieApplication : Application() {

    var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



        applicationComponent =
            DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule()).build()

    }

    fun applicationComponent(): ApplicationComponent? {
        if (applicationComponent == null) {
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule())
                .build()
        }

        return applicationComponent
    }
}