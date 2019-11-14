package com.example.erudy.presentation.presenter.view

import com.example.erudy.base.BaseView
import com.parse.ParseFile

interface ProfileView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun displayProfile(lastname : String, firstName: String, email : String, image : String)
    fun showError(errorMessage: String)
}