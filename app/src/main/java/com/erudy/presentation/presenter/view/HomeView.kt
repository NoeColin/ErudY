package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView
import com.erudy.data.entity.Request

interface HomeView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToRequestDetail(idRequest: String)
    fun displayRequests(requests: List<Request>)
    fun goToRequestCreation()
}