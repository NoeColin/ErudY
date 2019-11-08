package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
import com.example.erudy.presentation.presenter.view.RequestDetailView
import javax.inject.Inject

class RequestDetailFragmentPresenter
@Inject
constructor(): BasePresenter<RequestDetailView>() {

    fun signup() {
        view.displayLoader()

    }
}