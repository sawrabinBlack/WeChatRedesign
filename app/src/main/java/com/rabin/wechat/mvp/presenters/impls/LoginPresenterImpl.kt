package com.rabin.wechat.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.mvp.presenters.LoginPresenter
import com.rabin.wechat.mvp.views.LoginView

class LoginPresenterImpl:LoginPresenter,ViewModel() {

    val mModel:WechatModel=WechatModelImpl
  var mView : LoginView?=null
    override fun initView(view: LoginView) {
        mView=view
    }

    override fun onTapLogin(email: String, password: String) {
        mModel.login(email,password, OnSuccess = {
            mView?.navigateToMainPage()
        }, onFailure = {
            Log.d("LoginError",it)
            mView?.showError(it)
        })
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}