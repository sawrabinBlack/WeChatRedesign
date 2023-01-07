package com.rabin.wechat.mvp.presenters

import android.net.Uri
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.mvp.views.ProfileView

interface ProfilePresenter :BasePresenter{
    fun iniView(view:ProfileView)
    fun onTapEditPhoto()
    fun onTapQR()
    fun onTapEditProfile()
    fun onPhotoTaken(profilePhotoUri: Uri)
    fun onTapSaveProfile(user: UserVO)
}