package com.rabin.wechat.mvp.presenters.impls

import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.mvp.presenters.NewMomentPresenter
import com.rabin.wechat.mvp.views.NewMomentView

class NewMomentPresenterImpl : NewMomentPresenter, ViewModel() {
    var mView: NewMomentView? = null
    var mModel: WechatModel = WechatModelImpl
    override fun iniView(view: NewMomentView) {
        mView = view
    }

    override fun onTapAddMedia() {
        mView?.openVideoAndImageSelect()
    }

    override fun onTapCreateMoment(images: List<Uri>, description: String, isVideo: Boolean) {
        mModel.createNewMoment(images,description,isVideo,{
                                                          mView?.endSection()
        },{})
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mModel.getUserData(onSuccess = {
            mView?.showUserData(it)
        }, onFailure = {

        })
    }


}