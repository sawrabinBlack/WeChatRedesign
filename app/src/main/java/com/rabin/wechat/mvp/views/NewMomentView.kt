package com.rabin.wechat.mvp.views

import com.rabin.wechat.data.VOs.UserVO

interface NewMomentView:BaseView {
    fun showUserData(user:UserVO)
    fun openVideoAndImageSelect()
    fun endSection()

}