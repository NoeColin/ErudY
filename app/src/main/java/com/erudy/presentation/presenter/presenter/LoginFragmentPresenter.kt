package com.erudy.presentation.presenter.presenter

import android.content.Context
import com.erudy.R
import com.erudy.base.BasePresenter
import com.erudy.presentation.presenter.view.LoginView
import com.parse.ParseUser
import javax.inject.Inject

class LoginFragmentPresenter
@Inject
constructor(var context: Context): BasePresenter<LoginView>() {

    fun signin(userName: String, password: String) {
        view.displayLoader()
        ParseUser.logInInBackground(userName, password) {user, exception ->
            view.hideLoader()
            user?.let {
                view.goToMain()
            }
            exception?.let {
                view.showError(it.localizedMessage.toString())
            }

        }
    }

    fun forgetPassword(mail: String) {
        view.displayLoader()
        ParseUser.requestPasswordResetInBackground(mail) { error ->
            view.hideLoader()
            if (error == null) {
                view.showMessage(context.getString(R.string.email_sent))
            } else {
                view.showError(error.localizedMessage.toString())
            }
        }
    }
}