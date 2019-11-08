package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.presentation.presenter.view.RegisterView
import com.parse.ParseFile
import javax.inject.Inject

class RegisterFragmentPresenter
@Inject
constructor(): BasePresenter<RegisterView>() {

    fun signup(firstName: String, lastName: String, email: String, password: String, imageData: ByteArray) {
        view.displayLoader()
        var user = ErudyUser()
        user.firstName = firstName
        user.lastName = lastName
        user.email = email
        user.setPassword(password)
        user.profileImage = ParseFile("profileImage.jpg", imageData)

    }
}