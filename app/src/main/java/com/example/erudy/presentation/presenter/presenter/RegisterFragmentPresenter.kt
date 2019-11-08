package com.example.erudy.presentation.presenter.presenter

import android.graphics.Bitmap
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
constructor(): BasePresenter<RegisterView>() {

    fun signup(firstName: String, lastName: String, email: String, password: String, confirmPassword: String, bitmap: Bitmap) {
        view.displayLoader()
        if (!firstName.isBlank() && !lastName.isBlank() && email.isValidEmail() && password.length >= 8 && password == confirmPassword) {

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
                    view.showError("Erreur : Image non sauvegardée...")
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




        } else {
            view.hideLoader()
            view.showError("Tous les champs doivent être remplis et l'adresse mail doit être valide...")
        }





    }
}