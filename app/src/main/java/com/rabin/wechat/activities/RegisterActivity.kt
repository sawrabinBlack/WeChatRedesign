package com.rabin.wechat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.rabin.wechat.R
import com.rabin.wechat.mvp.presenters.RegisterPresenter
import com.rabin.wechat.mvp.presenters.impls.RegisterPresenterImpl
import com.rabin.wechat.mvp.views.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {
    lateinit var mPresenter: RegisterPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpOTP()
    }

    private fun setUpOTP() {
        btnGetOTP.setOnClickListener {
            etOTP1.requestFocus()
        }
        etOTP1.addTextChangedListener {
            etOTP2.requestFocus()
        }
        etOTP2.addTextChangedListener {
            etOTP3.requestFocus()
        }

        etOTP3.addTextChangedListener {
            etOTP4.requestFocus()
        }

        btnVerify.setOnClickListener {
            val OTP = "${etOTP1.text}${etOTP2.text}${etOTP3.text}${etOTP4.text}"
            val phone = etEnterYourPhoneRegister.text.toString()
            mPresenter.onTapVerify(phone, OTP)
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[RegisterPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    override fun navigateToFillUserData(phone: String) {
        startActivity(FillUserDataActivity.newIntent(this, phone))
    }




}