package com.erudy.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.erudy.R
import com.erudy.base.BaseFragment
import com.erudy.presentation.presenter.presenter.RequestCreationFragmentPresenter
import com.erudy.presentation.presenter.view.RequestCreationView
import com.erudy.presentation.ui.activity.ContainerActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_request_creation.*
import javax.inject.Inject


class RequestCreationFragment : BaseFragment<RequestCreationFragmentPresenter>(),
    RequestCreationView {

    @Inject
    override lateinit var presenter: RequestCreationFragmentPresenter
    override val layoutId: Int = R.layout.fragment_request_creation

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

    override fun goToList() {
        activity?.finish()
    }

    fun createRequest() {
        presenter.createRequest(edit_request_creation_title.text.toString(), edit_request_creation_content.text.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_request_button.setOnClickListener {
            createRequest()
        }
    }
}
