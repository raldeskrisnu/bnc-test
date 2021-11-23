package com.raldes.bnc.di.annotation.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)