package com.raldes.bnc.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.raldes.bnc.MovieApplication
import com.raldes.bnc.di.component.ApplicationComponent
import com.raldes.bnc.utils.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        ButterKnife.bind(this)
        resolveDaggerDependency()
        iniateViewModel()
    }

    protected open fun resolveDaggerDependency() {}

    protected open fun iniateViewModel() {}

    protected fun applicationComponent(): ApplicationComponent? {
        val application = application as MovieApplication
        return application.applicationComponent()
    }

    protected abstract fun getContentView(): Int


}