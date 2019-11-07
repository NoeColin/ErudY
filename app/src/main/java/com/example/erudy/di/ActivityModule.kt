package com.example.erudy.di

import com.example.erudy.presentation.ui.activity.LoginActivity
import com.example.erudy.presentation.ui.fragments.LoginFragment
import com.example.erudy.presentation.ui.fragments.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRegisterFragment(): RegisterFragment
}