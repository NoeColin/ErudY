package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView


interface RequestCreationView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToList()
}