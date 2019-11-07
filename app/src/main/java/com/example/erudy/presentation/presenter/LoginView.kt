package com.example.erudy.presentation.presenter

import com.example.erudy.base.BaseView

interface LoginView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToMain()
}