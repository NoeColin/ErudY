package com.example.erudy.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.erudy.R
import com.example.erudy.presentation.ui.fragments.EditProfileFragment
import com.example.erudy.presentation.ui.fragments.LoginFragment

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

    fun saveProfile(){

    }
}
