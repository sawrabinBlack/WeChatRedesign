package com.rabin.wechat.mvp.presenters.impls

import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.mvp.presenters.ProfilePresenter
import com.rabin.wechat.mvp.views.ProfileView

class ProfilePresenterImpl : ProfilePresenter, ViewModel() {
    var mView: ProfileView? = null
    var mUser: UserVO? = null
    var mModel: WechatModel = WechatModelImpl
    override fun iniView(view: ProfileView) {
        mView = view
    }

    override fun onTapEditPhoto() {

    }

    override fun onTapQR() {

    }

    override fun onTapEditProfile() {
        mUser?.let {
            mView?.showEditDialog(it)
        }

    }

    override fun onPhotoTaken(profilePhotoUri: Uri) {
        mUser?.let {
            mModel.updateUserPhoto(it,profilePhotoUri)
        }
    }

    override fun onTapSaveProfile(user: UserVO) {
        mModel.editUserProfile(user, {}, {})
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mModel.getUserData(onSuccess = {
            mUser = it
            mView?.showProfile(it)
        }, onFailure = {
            mView?.showError(it)
        })
    }
}