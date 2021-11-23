package com.raldes.bnc.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import com.raldes.bnc.di.component.DaggerLoginAcitivityComponent
import com.raldes.bnc.di.module.LoginActivityModule
import com.raldes.bnc.ui.BaseActivity
import com.raldes.bnc.viewmodel.LoginViewModel
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.raldes.bnc.R
import com.raldes.bnc.utils.PreferencesManager

class LoginActivity : BaseActivity() {

    @BindView(R.id.edit_text_email)
    lateinit var email:EditText

    @BindView(R.id.edit_text_password)
    lateinit var password:EditText

    @BindView(R.id.login_button)
    lateinit var loginButton:Button

    @BindView(R.id.progressbar)
    lateinit var progressBar: ProgressBar

    lateinit var loginViewModel: LoginViewModel

    var preferencesManager: PreferencesManager?=null

    override fun iniateViewModel() {
        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun resolveDaggerDependency() {
        DaggerLoginAcitivityComponent.builder()
            .applicationComponent(applicationComponent())
            .loginActivityModule(LoginActivityModule())
            .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferencesManager(this)

        var examplePref = preferencesManager?.loginToken

        if(examplePref !=null) {
            val movieIntentActivity = Intent(this@LoginActivity,
                MovieActivity::class.java)
            startActivity(movieIntentActivity)
        }

        setupLiveData()

        loginButton.setOnClickListener {
            if (isValidEmail(email.text.toString())) {
                preferencesManager = PreferencesManager(this)
                loginViewModel.atemmptLogin(email.text.toString(), password.text.toString())
                return@setOnClickListener
            } else {
                Toast.makeText(this@LoginActivity, "Email is not valid", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun setupLiveData() {
        loginViewModel.apply {
            loading.observe(this@LoginActivity, Observer {
                if(it) {
                    progressBar.visibility = View.VISIBLE

                } else {
                    progressBar.visibility = View.GONE
                }
            })

            success.observe(this@LoginActivity, Observer {
                if(it !=null) {

                    preferencesManager?.loginToken = it.token
                    val movieIntentActivity = Intent(this@LoginActivity,
                        MovieActivity::class.java)
                    startActivity(movieIntentActivity)
                } else {
                    Toast.makeText(this@LoginActivity, "Something went wrong", Toast.LENGTH_SHORT)
                }
            })
        }
    }

    override fun getContentView():Int {
        return R.layout.login_activity
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}