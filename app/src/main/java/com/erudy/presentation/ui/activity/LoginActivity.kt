package com.erudy.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.erudy.R
import com.erudy.presentation.ui.fragments.LoginFragment
import com.erudy.presentation.ui.fragments.RegisterFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, LoginFragment())
                .commit()
        }
    }

    fun goToLogin() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, LoginFragment())
            .commit()
    }

    fun goToRegister() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, RegisterFragment())
            .commit()
    }

}
