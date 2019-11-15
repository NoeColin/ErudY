package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView

interface LoginView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToMain()
    fun showMessage(message: String)
}