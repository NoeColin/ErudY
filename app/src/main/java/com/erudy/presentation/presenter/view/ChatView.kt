package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView
import com.erudy.data.entity.Conversation
import com.erudy.data.entity.Message

interface ChatView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun displayMessages(messages: List<Message>)
    fun createMessage()
    fun refreshChat()
    fun clearMessageInput()
}