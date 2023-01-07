package com.rabin.wechat.mvp.presenters

import android.net.Uri
import com.rabin.wechat.mvp.views.NewMomentView

interface NewMomentPresenter : BasePresenter {
    fun iniView(view: NewMomentView)
    fun onTapAddMedia()
    fun onTapCreateMoment(
        images: List<Uri>,
        description: String,
        isVideo: Boolean
    )
}