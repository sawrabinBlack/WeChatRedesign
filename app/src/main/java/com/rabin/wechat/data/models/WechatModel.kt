package com.rabin.wechat.data.models

import android.net.Uri
import com.rabin.wechat.data.VOs.MessageVO
import com.rabin.wechat.data.VOs.MomentVO
import com.rabin.wechat.data.VOs.UserVO

interface WechatModel {

    fun register(
        user: UserVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun login(email: String, password: String, OnSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getMoments(
        onSuccess: (moments: List<MomentVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getOTP(OnSuccess: (String) -> Unit, onFailure: (String) -> Unit)
    fun getUserData(onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit)
    fun editUserProfile(user: UserVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updateUserPhoto(user: UserVO, profilePhoto: Uri)
    fun getContacts(onSuccess: (List<UserVO>) -> Unit, onFailure: (String) -> Unit)
    fun getMessages(userId:String, onSuccess:(List<MessageVO>)->Unit, onFailure :(String)->Unit)
    fun sendNewMessage(message:MessageVO,receiverId: String,onSuccess:()->Unit, onFailure :(String)->Unit)
    fun createNewMoment(
        images: List<Uri>,
        description: String,
        isVideo: Boolean,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}