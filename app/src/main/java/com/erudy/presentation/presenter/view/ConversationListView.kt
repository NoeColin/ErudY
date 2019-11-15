package com.erudy.presentation.presenter.view

import com.erudy.base.BaseView
import com.erudy.data.entity.Conversation

interface ConversationListView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun displayConversations(convs: ArrayList<Conversation>)
    fun goToChat(conversation: Conversation)
}