package com.erudy.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erudy.R
import com.erudy.presentation.ui.fragments.EditProfileFragment
import com.erudy.presentation.ui.fragments.LoginFragment

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, EditProfileFragment())
                .commit()
        }
    }

}
