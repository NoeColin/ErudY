package com.erudy.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.erudy.R
import com.erudy.presentation.ui.fragments.ConversationListFragment
import dagger.android.AndroidInjection

class ConversationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, ConversationListFragment())
                .commit()
        }
    }
}