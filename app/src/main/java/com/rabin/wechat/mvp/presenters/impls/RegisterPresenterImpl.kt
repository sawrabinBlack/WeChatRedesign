package com.rabin.wechat.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.mvp.presenters.RegisterPresenter
import com.rabin.wechat.mvp.views.RegisterView

class RegisterPresenterImpl : RegisterPresenter, ViewModel() {
    var mModel: WechatModel = WechatModelImpl
    var mOTP = ""
    private var mView: RegisterView? = null
    override fun iniView(view: RegisterView) {
        mView = view
    }

    override fun onTapVerify(phone: String, OTP: String) {
        mModel.getOTP(OnSuccess = {
            mOTP = it
            if (OTP == mOTP) {
                mView?.navigateToFillUserData(phone)
            } else {

            }
        }, onFailure = {

        })



    }

    override fun onTapGetOTP() {
        mModel.getOTP(OnSuccess = {
            mOTP = it
        }, onFailure = {

        })
    }
}