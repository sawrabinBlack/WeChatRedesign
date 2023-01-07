package com.rabin.wechat.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rabin.wechat.mvp.presenters.WelcomePresenter
import com.rabin.wechat.mvp.views.LoginView
import com.rabin.wechat.mvp.views.WelcomeView

class WelcomePresenterImpl:WelcomePresenter, ViewModel() {
    var mView: WelcomeView?=null
    override fun iniView(view: WelcomeView) {
        mView=view
    }

    override fun onTapLogin() {
        mView?.navigateToLogin()
    }

    override fun onTapSignUp() {
        mView?.navigateToSignUp()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}