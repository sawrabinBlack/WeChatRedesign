package com.rabin.wechat.mvp.presenters

import com.rabin.wechat.mvp.views.MomentView

interface MomentPresenter:BasePresenter {
    fun iniView(view:MomentView)
    fun onTapNewMoment()
}