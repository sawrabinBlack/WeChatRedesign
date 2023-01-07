package com.rabin.wechat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rabin.wechat.R
import com.rabin.wechat.mvp.presenters.WelcomePresenter
import com.rabin.wechat.mvp.presenters.impls.WelcomePresenterImpl
import com.rabin.wechat.mvp.views.WelcomeView
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(),WelcomeView {
lateinit var mPresenter: WelcomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setUpOnClickListener()
        setUpPresenter()
    }

    private fun setUpPresenter() {
        mPresenter= ViewModelProvider(this)[WelcomePresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    private fun setUpOnClickListener() {
        btnSignUpWelcome.setOnClickListener {
            mPresenter.onTapSignUp()
        }

        btnLoginWelcome.setOnClickListener {
            mPresenter.onTapLogin()
        }
    }

    override fun navigateToLogin() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun navigateToSignUp() {
        startActivity(RegisterActivity.newIntent(this))
    }

    override fun showError(error: String) {

    }
}