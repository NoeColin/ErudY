package com.example.erudy.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.erudy.R
import com.example.erudy.base.BaseFragment
import com.example.erudy.presentation.presenter.presenter.RequestDetailFragmentPresenter
import com.example.erudy.presentation.presenter.view.RequestDetailView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_request_detail.*
import javax.inject.Inject


class RequestDetailFragment : BaseFragment<RequestDetailFragmentPresenter>(), RequestDetailView {

    @Inject
    override lateinit var presenter: RequestDetailFragmentPresenter
    override val layoutId: Int = R.layout.fragment_request_detail

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
