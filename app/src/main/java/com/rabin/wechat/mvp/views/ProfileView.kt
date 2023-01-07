package com.rabin.wechat.mvp.views

import com.rabin.wechat.data.VOs.UserVO

interface ProfileView:BaseView {
    fun showProfile(userVO: UserVO)
    fun showEditDialog(user:UserVO)
}