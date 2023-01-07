package com.rabin.wechat.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface BasePresenter {
    fun onUiReady(owner: LifecycleOwner)
}