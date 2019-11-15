package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView
import com.erudy.data.entity.Conversation
import com.erudy.data.entity.Request

interface RequestDetailView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun displayRequest(request: Request)
    fun checkConv(request: Request)
    fun goToChat(conversation: Conversation)
}