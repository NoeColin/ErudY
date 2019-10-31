package com.example.erudy.base

abstract class BasePresenter<out V : BaseView> {

    var unsafeView: BaseView? = null
        private set

    val view: V
        get() = unsafeView!! as V

    fun attach(v: BaseView) {
        this.unsafeView = v
    }

    fun detach() {
        unsafeView = null
    }
}