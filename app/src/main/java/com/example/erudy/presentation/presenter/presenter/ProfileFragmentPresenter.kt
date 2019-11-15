package com.example.erudy.presentation.presenter.presenter

import android.content.Context
import android.graphics.Bitmap
import com.example.erudy.R
import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.presentation.presenter.view.ProfileView
import com.example.erudy.utils.isValidEmail
import com.parse.ParseException
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ProfileFragmentPresenter
 @Inject
    constructor(): BasePresenter<ProfileView>(){
    fun loadProfile() {
        var currentUser = ParseUser.getCurrentUser() as ErudyUser
        currentUser.fetchInBackground { user: ErudyUser?, exception: ParseException? ->
            view.hideLoader()
            user?.let {
                view.displayProfile(it.lastName.toString(), it.firstName.toString(), it.email.toString(), it.profileImage!!.url)
            }
            exception?.let {
                view.showError(it.localizedMessage.toString())
            }
        }
    }
}
