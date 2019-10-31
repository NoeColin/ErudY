package com.example.erudy

import android.app.Application
import com.parse.Parse
import com.parse.ParseInstallation

class ErudYApp: Application() {

    override fun onCreate() {
        super.onCreate()

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