package com.rabin.wechat.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.mvp.presenters.MomentPresenter
import com.rabin.wechat.mvp.views.MomentView

class MomentPresenterImpl : MomentPresenter, ViewModel() {
    var mView: MomentView? = null
    var mModel: WechatModel = WechatModelImpl
    override fun iniView(view: MomentView) {
        mView = view
    }

    override fun onTapNewMoment() {
        mView?.navigateToNewMoment()
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mModel.getMoments(onSuccess = {
            mView?.showMomentList(it)
        }, onFailure = {

        })
    }
}