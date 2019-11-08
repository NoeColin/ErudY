package com.example.erudy.presentation.presenter.view

import com.example.erudy.base.BaseView

interface RegisterView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToMain()
}