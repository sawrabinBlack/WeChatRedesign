package com.rabin.wechat.mvp.presenters

import com.rabin.wechat.mvp.views.WelcomeView

interface WelcomePresenter:BasePresenter {
    fun iniView(view: WelcomeView)
    fun onTapLogin()
    fun onTapSignUp()
}