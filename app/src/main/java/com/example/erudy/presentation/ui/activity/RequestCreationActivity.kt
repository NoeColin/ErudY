package com.example.erudy.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.erudy.R
import com.example.erudy.presentation.ui.fragments.LoginFragment
import com.example.erudy.presentation.ui.fragments.RequestCreationFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_container.view.*

class RequestCreationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, RequestCreationFragment())
                .commit()
        }
    }
}
