package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.presentation.presenter.view.ProfileView
import com.parse.ParseException
import com.parse.ParseUser
import javax.inject.Inject

class ProfileFragmentPresenter
 @Inject
    constructor(): BasePresenter<ProfileView>(){
    fun loadProfile() {
        var currentUser = ParseUser.getCurrentUser() as ErudyUser
        currentUser.fetchInBackground { user: ErudyUser?, exception: ParseException? ->
            if(exception == null) {
                view.displayProfile(user!!.lastName.toString(), user.firstName.toString(), user.email.toString(), user.profileImage!!.url)
                view.hideLoader()
            }
        }
    }
}
