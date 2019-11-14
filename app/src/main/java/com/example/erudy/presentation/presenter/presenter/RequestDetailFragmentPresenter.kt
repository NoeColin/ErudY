package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
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

}