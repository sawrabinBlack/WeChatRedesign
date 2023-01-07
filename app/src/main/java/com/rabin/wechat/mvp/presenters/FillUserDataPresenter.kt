package com.rabin.wechat.mvp.presenters

import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.mvp.views.FillUserDataView

interface FillUserDataPresenter {
    fun iniView(view: FillUserDataView)
    fun onTapRegister(user:UserVO,userName:String)
}