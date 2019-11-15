package com.example.erudy.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.erudy.R
import com.example.erudy.base.BaseFragment
import com.example.erudy.data.entity.Message
import com.example.erudy.presentation.presenter.presenter.ChatFragmentPresenter
import com.example.erudy.presentation.presenter.view.ChatView
import com.example.erudy.presentation.ui.adapter.ChatAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_chat.*
import javax.inject.Inject

class ChatFragment(val conversationId: String) : BaseFragment<ChatFragmentPresenter>(), ChatView {

    @Inject
    override lateinit var presenter: ChatFragmentPresenter
    override val layoutId: Int = R.layout.fragment_chat

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        presenter.loadMessages(conversationId)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_message_button.setOnClickListener {
            createMessage()
        }

        message_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val messageList : List<Message> = ArrayList()
        val adapter = ChatAdapter(messageList)
        message_list.adapter = adapter
    }

    override fun displayLoader() {
        loader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        loader.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun displayMessages(messages: List<Message>) {
        val adapter = ChatAdapter(messages)
        message_list.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun createMessage() {
        presenter.createMessage(my_message.text.toString(), conversationId)
    }

    override fun refreshChat() {
        presenter.loadMessages(conversationId)
    }

    override fun clearMessageInput() {
        my_message.setText("")
    }
}