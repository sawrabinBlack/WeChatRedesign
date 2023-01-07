package com.rabin.wechat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.rabin.wechat.R
import com.rabin.wechat.mvp.presenters.LoginPresenter
import com.rabin.wechat.mvp.presenters.impls.LoginPresenterImpl
import com.rabin.wechat.mvp.views.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginView {

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,LoginActivity::class.java)
        }
    }
    lateinit var mPresenter:LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpPresenter()
        setUpOnClickListener()

    }

    private fun setUpOnClickListener() {
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etEnterYourPhone.text.toString(),etEnterYourPassword.text.toString())
        }
    }

    private fun setUpPresenter() {
        mPresenter= ViewModelProvider(this)[LoginPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    override fun navigateToMainPage() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun showError(error: String) {

    }
}