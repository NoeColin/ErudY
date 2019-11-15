package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.Conversation
import com.example.erudy.presentation.presenter.view.ConversationListView
import com.parse.ParseQuery
import com.parse.ParseUser
import javax.inject.Inject

class ConversationListFragmentPresenter
@Inject
constructor() : BasePresenter<ConversationListView>() {

    fun loadConvs() {
        view.displayLoader()
        var convList = ArrayList<Conversation>()

        var query1 = ParseQuery.getQuery<Conversation>(Conversation::class.java)
        query1.include("user1")
            .include("user2")
            .include("request")
            .whereEqualTo("user1", ParseUser.getCurrentUser())
            .orderByDescending("createdAt")
            .findInBackground { convs, error ->
                convList.addAll(convs)
                if (error != null) {
                    view.hideLoader()
                    view.showError(error.localizedMessage.toString())
                } else {
                    var query2 = ParseQuery.getQuery<Conversation>(Conversation::class.java)
                    query2.include("user1")
                        .include("user2")
                        .include("request")
                        .whereEqualTo("user2", ParseUser.getCurrentUser())
                        .orderByDescending("createdAt")
                        .findInBackground { convs, error ->
                            convList.addAll(convs)
                            view.hideLoader()
                            if (error != null) {
                                view.showError(error.localizedMessage.toString())
                            } else {
                                //TODO trié dans l'ordre des derniers messages reçus
                                view.displayConversations(convList)
                            }
                        }
                }
            }
    }
}