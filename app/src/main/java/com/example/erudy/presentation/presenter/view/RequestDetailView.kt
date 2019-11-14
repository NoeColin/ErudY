package com.example.erudy.presentation.presenter.view

import com.example.erudy.base.BaseView
import com.example.erudy.data.entity.Request

interface RequestDetailView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun displayRequest(request: Request)
}