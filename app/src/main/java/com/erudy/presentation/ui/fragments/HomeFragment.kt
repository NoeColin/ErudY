package com.erudy.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.textclassifier.TextLinks
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erudy.R
import com.erudy.base.BaseFragment
import com.erudy.data.entity.Request
import com.erudy.presentation.presenter.presenter.HomeFragmentPresenter
import com.erudy.presentation.presenter.view.HomeView
import com.erudy.presentation.ui.activity.ContainerActivity
import com.erudy.presentation.ui.activity.RequestCreationActivity
import com.erudy.presentation.ui.adapter.RequestAdapter
import com.parse.ParseQuery
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeFragmentPresenter>(), HomeView, RequestAdapter.ClickOnRecycler {
    @Inject
    override lateinit var presenter: HomeFragmentPresenter
    override val layoutId: Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        presenter.loadRequests()
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

    override fun displayRequests(requests: List<Request>) {
        val adapter = RequestAdapter(requests, this)
        request_list.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        new_request_button.setOnClickListener {
            goToRequestCreation()
        }

        request_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val requestList : List<Request> = ArrayList()
        val adapter = RequestAdapter(requestList, this)
        request_list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.loadRequests()
    }

    override fun goToRequestCreation() {
        val intent = Intent(context, RequestCreationActivity::class.java)
        startActivity(intent)
    }

    override fun goToRequestDetail(idRequest: String) {
        (activity as ContainerActivity).gotToRequestDetail(idRequest)
    }

    override fun requestClicked(idRequest: String) {
        goToRequestDetail(idRequest)
    }
}