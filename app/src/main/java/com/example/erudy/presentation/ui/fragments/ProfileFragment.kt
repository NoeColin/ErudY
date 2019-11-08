package com.example.erudy.presentation.ui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide

import com.example.erudy.R
import com.example.erudy.base.BaseFragment
import com.example.erudy.presentation.presenter.presenter.ProfileFragmentPresenter
import com.example.erudy.presentation.presenter.view.ProfileView
import com.example.erudy.presentation.ui.activity.LoginActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.loader
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment() :BaseFragment<ProfileFragmentPresenter>(), ProfileView {

    @Inject
    override lateinit var presenter: ProfileFragmentPresenter
    override val layoutId: Int = R.layout.fragment_profile


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

    override fun displayProfile(lastname: String, firstName: String, email: String, image: String) {
        firstnameField.text = firstName
        nameField.text = lastname
        emailField.text = email
        Glide.with(context!!).load(image).into(profileImage)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            presenter.loadProfile()

        editButton.setOnClickListener {
            (activity as LoginActivity).goToRegister()
        }
    }


}
