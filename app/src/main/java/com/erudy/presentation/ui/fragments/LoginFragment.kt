package com.erudy.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.erudy.R
import com.erudy.base.BaseFragment
import com.erudy.presentation.presenter.presenter.LoginFragmentPresenter
import com.erudy.presentation.presenter.view.LoginView
import com.erudy.presentation.ui.activity.ContainerActivity
import com.erudy.presentation.ui.activity.LoginActivity
import com.parse.ParseInstallation
import com.parse.ParsePush
import com.parse.ParseUser
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : BaseFragment<LoginFragmentPresenter>(), LoginView {

    @Inject
    override lateinit var presenter: LoginFragmentPresenter
    override val layoutId: Int = R.layout.fragment_login

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

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun goToMain() {
        val installation = ParseInstallation.getCurrentInstallation()
        installation.put("user", ParseUser.getCurrentUser())
        installation.saveInBackground()
        ParsePush.subscribeInBackground("Requests")
        val intent = Intent(context, ContainerActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener {
            presenter.signin(edit_login_email.text.toString(), edit_login_password.text.toString())
        }
        register.setOnClickListener {
            (activity as LoginActivity).goToRegister()
        }
        forgot_password.setOnClickListener {
            presenter.forgetPassword(edit_login_email.text.toString())
        }
    }
}