package com.example.erudy.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.erudy.R
import com.example.erudy.base.BaseFragment
import com.example.erudy.presentation.presenter.presenter.RegisterFragmentPresenter
import com.example.erudy.presentation.presenter.view.RegisterView
import com.example.erudy.presentation.ui.activity.LoginActivity
import com.example.erudy.presentation.ui.activity.MainActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.loader
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

class RegisterFragment : BaseFragment<RegisterFragmentPresenter>(), RegisterView {

    @Inject
    override lateinit var presenter: RegisterFragmentPresenter
    override val layoutId: Int = R.layout.fragment_register

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
        login.setOnClickListener {
            (activity as LoginActivity).goToLogin()
        }
        save_button.setOnClickListener {
            signup()
        }
    }

    fun signup() {
        val bitmap = (profile_image.drawable.current as BitmapDrawable).bitmap
        presenter.signup(edit_first_name.text.toString(), edit_last_name.text.toString(), edit_email.text.toString(), edit_password.text.toString(), edit_confirm_password.text.toString(), bitmap)
    }

    override fun goToMain() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}