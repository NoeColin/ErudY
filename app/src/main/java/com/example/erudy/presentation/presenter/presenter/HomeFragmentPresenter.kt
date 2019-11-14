package com.example.erudy.presentation.presenter.presenter

import android.widget.Toast
import com.example.erudy.base.BasePresenter
import com.example.erudy.data.entity.Request
import com.example.erudy.presentation.presenter.view.HomeView
import com.example.erudy.presentation.ui.adapter.RequestAdapter
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragmentPresenter
@Inject
constructor(): BasePresenter<HomeView>() {

    fun loadRequests() {
        view.displayLoader()
        var query = ParseQuery.getQuery<Request>(Request::class.java)
        query.include("title")
        query.include("description")
        query.include("owner")
        query.findInBackground { requests, error ->
            view.hideLoader()
            if (error != null) {
                view.showError(error.localizedMessage.toString())
            } else {
                view.displayRequests(requests)
            }
        }
    }

}