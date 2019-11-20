package com.erudy.di

import com.erudy.presentation.ui.activity.*
import com.erudy.presentation.ui.fragments.*
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
    internal abstract fun contributeEditProfileActivity(): EditProfileActivity

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

    @ContributesAndroidInjector
    internal abstract fun contributeChatActivity(): ChatActivity

    @ContributesAndroidInjector
    internal abstract fun contributeChatFragment(): ChatFragment

    @ContributesAndroidInjector
    internal abstract fun contributeConversationListActivity(): ConversationListActivity

    @ContributesAndroidInjector
    internal abstract fun contributeConversationListFragment(): ConversationListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSplashScreenActivity(): SplashScreenActivity
}