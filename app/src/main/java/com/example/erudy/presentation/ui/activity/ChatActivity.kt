package com.example.erudy.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.erudy.R
import com.example.erudy.presentation.ui.fragments.ChatFragment
import dagger.android.AndroidInjection

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        var conversationId = intent.getStringExtra("currentconv")


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, ChatFragment(conversationId))
                .commit()
        }
    }
}
