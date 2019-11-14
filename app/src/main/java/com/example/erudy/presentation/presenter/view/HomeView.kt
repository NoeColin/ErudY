package com.example.erudy.presentation.presenter.view

import com.example.erudy.base.BaseView
import com.example.erudy.data.entity.Request

interface HomeView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToRequestDetail(idRequest: String)
    fun displayRequests(requests: List<Request>)
}