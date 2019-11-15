package com.example.erudy.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.erudy.R
import com.example.erudy.base.BaseFragment
import com.example.erudy.data.entity.Conversation
import com.example.erudy.presentation.presenter.presenter.ConversationListFragmentPresenter
import com.example.erudy.presentation.presenter.view.ConversationListView
import com.example.erudy.presentation.ui.activity.ChatActivity
import com.example.erudy.presentation.ui.activity.ConversationListActivity
import com.example.erudy.presentation.ui.adapter.ConversationAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_conversation_list.*
import javax.inject.Inject


class ConversationListFragment : BaseFragment<ConversationListFragmentPresenter>(),
    ConversationListView, ConversationAdapter.ClickOnRecycler {
    @Inject
    override lateinit var presenter: ConversationListFragmentPresenter
    override val layoutId: Int = R.layout.fragment_conversation_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        presenter.loadConvs()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
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

    override fun displayConversations(conversations: ArrayList<Conversation>) {
        val adapter = ConversationAdapter(conversations, this)
        conv_list.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        conv_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val convList : List<Conversation> = ArrayList()
        val adapter = ConversationAdapter(convList, this)
        conv_list.adapter = adapter
    }

    override fun goToChat(conversation: Conversation) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra("currentconv", conversation.objectId)
        startActivity(intent)
    }

    override fun convClicked(conv: Conversation) {
        goToChat(conv)
    }
}