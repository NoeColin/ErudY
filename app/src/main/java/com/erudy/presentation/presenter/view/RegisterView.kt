package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView

interface RegisterView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToMain()
}