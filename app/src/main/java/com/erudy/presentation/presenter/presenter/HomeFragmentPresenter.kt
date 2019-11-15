package com.erudy.presentation.presenter.presenter

import com.erudy.base.BasePresenter
import com.erudy.data.entity.Request
import com.erudy.presentation.presenter.view.HomeView
import com.parse.ParseQuery
import javax.inject.Inject

class HomeFragmentPresenter
@Inject
constructor() : BasePresenter<HomeView>() {

    fun loadRequests() {
        view.displayLoader()
        var query = ParseQuery.getQuery<Request>(Request::class.java)
        query.include("title")
            .include("description")
            .include("owner")
            .orderByDescending("createdAt")
            .findInBackground { requests, error ->
                view.hideLoader()
                if (error != null) {
                    view.showError(error.localizedMessage.toString())
                } else {
                    view.displayRequests(requests)
                }
            }
    }

}