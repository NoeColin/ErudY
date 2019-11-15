package com.example.erudy.presentation.presenter.view

import com.example.erudy.base.BaseView
import com.example.erudy.data.entity.Conversation

interface ConversationListView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun displayConversations(convs: ArrayList<Conversation>)
    fun goToChat(conversation: Conversation)
}