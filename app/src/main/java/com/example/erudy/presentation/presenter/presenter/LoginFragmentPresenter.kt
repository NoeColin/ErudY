package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
import com.example.erudy.presentation.presenter.view.LoginView
import com.parse.ParseUser
import javax.inject.Inject

class LoginFragmentPresenter
@Inject
constructor(): BasePresenter<LoginView>() {

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
}