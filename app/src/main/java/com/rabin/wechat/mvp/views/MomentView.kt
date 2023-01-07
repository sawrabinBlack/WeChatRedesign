package com.rabin.wechat.mvp.views

import com.rabin.wechat.data.VOs.MomentVO

interface MomentView {

    fun showMomentList(moments:List<MomentVO>)
    fun navigateToNewMoment()
}