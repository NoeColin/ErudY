package com.example.erudy.presentation.presenter.presenter

import com.example.erudy.base.BasePresenter
import com.example.erudy.presentation.presenter.view.RegisterView
import javax.inject.Inject

class RequestDetailFragmentPresenter
@Inject
constructor(): BasePresenter<RegisterView>() {

    fun signup() {
        view.displayLoader()

    }
}