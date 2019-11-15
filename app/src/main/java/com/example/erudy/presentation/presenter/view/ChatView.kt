package com.example.erudy.presentation.presenter.view

import com.example.erudy.base.BaseView
import com.example.erudy.data.entity.Conversation
import com.example.erudy.data.entity.Message

interface ChatView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun displayMessages(messages: List<Message>)
    fun createMessage()
    fun refreshChat()
}