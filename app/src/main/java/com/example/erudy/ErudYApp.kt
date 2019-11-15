package com.example.erudy

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.erudy.data.entity.Conversation
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.data.entity.Message
import com.example.erudy.data.entity.Request
import com.example.erudy.di.AppModule
import com.example.erudy.di.DaggerAppComponent
import com.example.erudy.utils.MediaLoader
import com.parse.Parse
import com.parse.ParseInstallation
import com.parse.ParseObject
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class ErudYApp: Application(), HasActivityInjector, HasSupportFragmentInjector{


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initParse()
        initAlbum()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

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
        ParseObject.registerSubclass(Conversation::class.java)
        ParseObject.registerSubclass(ErudyUser::class.java)
        ParseObject.registerSubclass(Message::class.java)
        ParseObject.registerSubclass(Request::class.java)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.appId))
                .clientKey(getString(R.string.clientKey))
                .server(getString(R.string.serverUrl))
                .build()
        )
        ParseInstallation.getCurrentInstallation().saveInBackground()
    }

    private fun initAlbum() {
        Album.initialize(AlbumConfig.newBuilder(this)
            .setAlbumLoader(MediaLoader())
            .build())
    }
    
}