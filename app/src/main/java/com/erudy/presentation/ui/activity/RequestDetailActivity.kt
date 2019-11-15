package com.erudy.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erudy.R
import com.erudy.presentation.ui.fragments.LoginFragment
import com.erudy.presentation.ui.fragments.RequestDetailFragment
import dagger.android.AndroidInjection

class RequestDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, RequestDetailFragment())
                .commit()
        }
    }


}
