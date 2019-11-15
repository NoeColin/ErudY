package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView

interface EditProfileView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun displayProfile(lastname : String, firstName: String, email : String, image : String)
    fun showError(errorMessage: String)
    fun goToProfile()
}