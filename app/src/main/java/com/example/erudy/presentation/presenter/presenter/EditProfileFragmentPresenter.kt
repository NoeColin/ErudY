package com.example.erudy.presentation.presenter.presenter

import android.content.Context
import android.graphics.Bitmap
import com.example.erudy.R
import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.presentation.presenter.view.EditProfileView
import com.example.erudy.utils.isValidEmail
import com.parse.ParseException
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class EditProfileFragmentPresenter
    @Inject
    constructor(var context : Context) : BasePresenter<EditProfileView>() {
    var currentUser = ParseUser.getCurrentUser() as ErudyUser
    fun loadProfile() {

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

    fun updateProfile(firstName: String, lastName: String, email: String, bitmap: Bitmap) {

        if (firstName.isBlank() && lastName.isBlank() && email.isBlank()) {
            view.showError(context.getString(R.string.error_register_all_fields))
        } else if (!email.isValidEmail()) {
            view.showError(context.getString(R.string.error_register_mail_not_valid))
        } else {
            view.displayLoader()
            val bitmapStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bitmapStream)
            val bitMapData = bitmapStream.toByteArray()
            var user = currentUser
            user.firstName = firstName
            user.lastName = lastName
            user.email = email
            user.username = email
            var profileImage = ParseFile("profileImage.jpg", bitMapData)
            profileImage.saveInBackground { e: ParseException? ->
                if (e == null) {
                    user.profileImage = profileImage
                } else {
                    view.showError(context.getString(R.string.error_image_save))
                }
                user.saveInBackground {
                    view.hideLoader()
                    if (it == null) {
                        view.goToProfile()
                    } else {
                        view.showError(it.localizedMessage.toString())
                    }
                }
            }
        }
    }
}
