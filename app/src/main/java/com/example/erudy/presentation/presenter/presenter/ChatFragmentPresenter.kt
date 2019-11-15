package com.example.erudy.presentation.presenter.presenter

import android.content.Context
import com.example.erudy.R
import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.Conversation
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.data.entity.Message
import com.example.erudy.presentation.presenter.view.ChatView
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import javax.inject.Inject

class ChatFragmentPresenter
@Inject
constructor(var context: Context): BasePresenter<ChatView>() {

    fun loadMessages(conversationId: String) {
        view.displayLoader()

        var convQuery = ParseQuery.getQuery(Conversation::class.java)
        convQuery.getInBackground(conversationId) { conversation: Conversation?, error: ParseException? ->
            conversation?.let {
                var query = ParseQuery.getQuery<Message>(Message::class.java)
                query.include("content")
                query.include("writer")
                query.whereEqualTo("conversation", it)

                query.findInBackground { messages, error ->
                    view.hideLoader()
                    if (error != null) {
                        view.showError(error.localizedMessage.toString())
                    } else {
                        view.displayMessages(messages)
                    }
                }
            }
            error?.let {
                view.hideLoader()
                view.showError(it.localizedMessage.toString())
            }
        }
    }

    fun createMessage(message: String, conversationId: String) {

        if (message.isNotBlank()) {
            view.displayLoader()

            var query = ParseQuery.getQuery(Conversation::class.java)
            query.getInBackground(conversationId) {conversation: Conversation?, error: ParseException? ->
                conversation?.let {
                    val currentUser = ParseUser.getCurrentUser() as ErudyUser
                    val newMessage = Message()
                    newMessage.content = message
                    newMessage.conversation = it
                    newMessage.writer = currentUser

                    newMessage.saveInBackground {
                        view.hideLoader()
                        if (it == null) {
                            loadMessages(newMessage.conversation!!.objectId)
                            view.clearMessageInput()
                        } else {
                            view.showError(it.localizedMessage.toString())
                        }
                    }
                }
                error?.let {
                    view.hideLoader()
                    view.showError(it.localizedMessage.toString())
                }
            }


        }
    }
}