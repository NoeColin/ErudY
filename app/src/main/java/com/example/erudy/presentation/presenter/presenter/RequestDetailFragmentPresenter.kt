package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.Conversation
import com.example.erudy.data.entity.ErudyUser
import com.example.erudy.data.entity.Request
import com.example.erudy.presentation.presenter.view.RequestDetailView
import com.parse.ParseException
import com.parse.ParseQuery
import javax.inject.Inject

class RequestDetailFragmentPresenter
@Inject
constructor(): BasePresenter<RequestDetailView>() {

    fun loadRequestDetail(idrequest: String?) {
        view.displayLoader()
        var query = ParseQuery<Request>(Request::class.java)
        query.include("title")
        query.include("description")
        query.include("owner")
        query.getInBackground(idrequest) { request: Request?, error: ParseException? ->
            view.hideLoader()
            error?.let {
                view.showError(it.localizedMessage.toString())
            }
            request?.let {
                view.displayRequest(request)
            }
        }
    }

    fun checkConv(owner: ErudyUser?, answerer: ErudyUser, request: Request) {

        view.displayLoader()
        var query = ParseQuery<Conversation>(Conversation::class.java)
        query.whereEqualTo("user1", owner)
        query.whereEqualTo("user2", answerer)
        query.whereEqualTo("request", request)

        query.findInBackground { conversation, error: ParseException? ->

            error?.let {
                view.showError(it.localizedMessage.toString())
            }
            conversation?.let {
                if (it.isEmpty()) {
                    createConv(owner!!, answerer, request)
                } else {
                    view.hideLoader()
                    view.goToChat(it.first())
                }
            }
        }
    }

    fun createConv(owner: ErudyUser, answerer: ErudyUser, request: Request) {
        var conversation = Conversation()
        conversation.user1 = owner
        conversation.user2 = answerer
        conversation.request = request

        conversation.saveInBackground {
            view.hideLoader()
            if (it == null) {
                view.hideLoader()
                view.goToChat(conversation)
            } else {
                view.showError(it.localizedMessage.toString())
            }
        }
    }

}