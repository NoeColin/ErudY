package com.example.erudy.di

import com.example.erudy.presentation.ui.activity.LoginActivity
import com.example.erudy.presentation.ui.activity.RequestCreationActivity
import com.example.erudy.presentation.ui.activity.RequestDetailActivity
import com.example.erudy.presentation.ui.fragments.*
import com.example.erudy.presentation.ui.fragments.LoginFragment
import com.example.erudy.presentation.ui.fragments.ProfileFragment
import com.example.erudy.presentation.ui.fragments.RegisterFragment
import com.example.erudy.presentation.ui.fragments.EditProfileFragment
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

    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun contributeEditProfileFragment(): EditProfileFragment

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRequestCreationActivity(): RequestCreationActivity

    @ContributesAndroidInjector
    internal abstract fun contributeRequestCreationFragment(): RequestCreationFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRequestDetailActivity(): RequestDetailActivity

    @ContributesAndroidInjector
    internal abstract fun contributeRequestDetailFragment(): RequestDetailFragment
}