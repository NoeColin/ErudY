package com.example.erudy.presentation.presenter.presenter

import android.content.Context
import android.graphics.Bitmap
import com.example.erudy.R
import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.presentation.presenter.view.RegisterView
import com.example.erudy.utils.isValidEmail
import com.parse.ParseException
import com.parse.ParseFile
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class RegisterFragmentPresenter
@Inject
constructor(var context: Context): BasePresenter<RegisterView>() {

    fun signup(firstName: String, lastName: String, email: String, password: String, confirmPassword: String, bitmap: Bitmap) {


        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            view.showError(context.getString(R.string.error_register_all_fields))
        } else if (!email.isValidEmail()) {
            view.showError(context.getString(R.string.error_register_mail_not_valid))
        } else if (password.length < 8) {
            view.showError(context.getString(R.string.error_register_password_short))
        } else if (password != confirmPassword) {
            view.showError(context.getString(R.string.error_register_password_not_corresponding))
        } else {
            view.displayLoader()
            val bitmapStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bitmapStream)
            val bitMapData = bitmapStream.toByteArray()
            var user = ErudyUser()
            user.firstName = firstName
            user.lastName = lastName
            user.email = email
            user.username = email
            user.setPassword(password)
            var profileImage = ParseFile("profileImage.jpg", bitMapData)
            profileImage.saveInBackground { e: ParseException? ->
                if (e == null) {
                    user.profileImage = profileImage
                } else {
                    view.showError(context.getString(R.string.error_image_save))
                }
                user.signUpInBackground {
                    view.hideLoader()
                    if (it == null) {
                        view.goToMain()
                    } else {
                        view.showError(it.localizedMessage.toString())
                    }
                }
            }
        }
    }
}