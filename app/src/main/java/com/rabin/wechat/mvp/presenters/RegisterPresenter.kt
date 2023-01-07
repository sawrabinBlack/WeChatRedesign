package com.rabin.wechat.mvp.presenters

import com.rabin.wechat.mvp.views.RegisterView

interface RegisterPresenter {
    fun iniView(view:RegisterView)
    fun onTapVerify(phone:String,OTP : String)
    fun onTapGetOTP()
}