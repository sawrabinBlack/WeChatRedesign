package com.rabin.wechat.mvp.presenters.impls

import androidx.lifecycle.ViewModel
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.mvp.presenters.FillUserDataPresenter
import com.rabin.wechat.mvp.views.FillUserDataView

class FillUserDataPresenterImpl : FillUserDataPresenter, ViewModel() {
    var mView: FillUserDataView? = null
    private val mModel: WechatModel = WechatModelImpl
    override fun iniView(view: FillUserDataView) {
        mView = view
    }

    override fun onTapRegister(user: UserVO, userName: String) {
        mModel.register(
            user,
            onSuccess = {
                mView?.navigateToMainScreen()
            }
        ) {

        }
    }
}
