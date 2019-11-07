package com.example.erudy

import android.app.Activity
import android.app.Application
import com.example.erudy.di.AppModule
import com.example.erudy.di.DaggerAppComponent
import com.parse.Parse
import com.parse.ParseInstallation
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class ErudYApp: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initParse()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    fun initDagger(){
        DaggerAppComponent
            .builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }

    fun initTimber(){
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())
    }

    fun initParse() {
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.appId))
                .clientKey(getString(R.string.clientKey))
                .server(getString(R.string.serverUrl))
                .build()
        )
        ParseInstallation.getCurrentInstallation().saveInBackground()
    }
}