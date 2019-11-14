package com.example.erudy.presentation.presenter.presenter

import android.content.Context
import com.example.erudy.R
import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.data.entity.Request
import com.example.erudy.presentation.presenter.view.RequestCreationView
import com.parse.ParseUser
import javax.inject.Inject


class RequestCreationFragmentPresenter
@Inject
constructor(var context: Context): BasePresenter<RequestCreationView>() {

    fun createRequest(title: String, content: String) {
        val currentUser = ParseUser.getCurrentUser() as ErudyUser

        if (title.isBlank() || content.isBlank()) {
            view.showError(context.getString(R.string.error_register_all_fields))
        } else {
            view.displayLoader()

            val newRequest = Request()
            newRequest.title = title
            newRequest.description = content
            newRequest.owner = currentUser

            newRequest.saveInBackground {
                view.hideLoader()
                if (it == null) {
                    view.goToList()
                } else {
                    view.showError(it.localizedMessage.toString())
                }
            }
        }
    }
}