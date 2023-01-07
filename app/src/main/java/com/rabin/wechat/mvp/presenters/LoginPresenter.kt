package com.rabin.wechat.mvp.presenters

import com.rabin.wechat.mvp.views.LoginView

interface LoginPresenter : BasePresenter {
    fun initView(view:LoginView)
    fun onTapLogin(email:String,password:String)
}