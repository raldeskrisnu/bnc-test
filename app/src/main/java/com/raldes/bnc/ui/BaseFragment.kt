package com.raldes.bnc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.raldes.bnc.MovieApplication
import com.raldes.bnc.di.component.ApplicationComponent
import com.raldes.bnc.utils.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val root = inflater.inflate(getContentView(), container, false)

        return root
    }

    protected abstract fun getContentView(): Int

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this,view)
        resolveDaggerDependency()
        iniateViewModel()
    }

    protected open fun iniateViewModel() {}

    protected open fun resolveDaggerDependency() {}

    protected fun applicationComponent(): ApplicationComponent? {
        return if (activity != null)
            (requireActivity().application as MovieApplication).applicationComponent()
        else
            null
    }
}