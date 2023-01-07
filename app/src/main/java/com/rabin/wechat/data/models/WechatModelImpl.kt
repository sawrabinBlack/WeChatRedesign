package com.rabin.wechat.data.models

import android.net.Uri
import android.util.Log
import com.rabin.wechat.data.VOs.MessageVO
import com.rabin.wechat.data.VOs.MomentVO
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.network.CloudFirestoreDatabaseApiImpl
import com.rabin.wechat.network.FirebaseApi
import com.rabin.wechat.network.FirebaseChatApi
import com.rabin.wechat.network.RealTimeDatabaseApiImpl
import com.rabin.wechat.network.auth.AuthManager
import com.rabin.wechat.network.auth.FirebaseAuthManagerImpl

object WechatModelImpl : WechatModel {
    val mApi: FirebaseApi = CloudFirestoreDatabaseApiImpl
    val mAuth: AuthManager = FirebaseAuthManagerImpl
    val mRealTime: FirebaseChatApi = RealTimeDatabaseApiImpl
    override fun register(
        user: UserVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mAuth.register(user, onSuccess, onFailure)
    }

    override fun login(
        email: String,
        password: String,
        OnSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuth.login(
            email = "$email@gmail.com",
            password = password,
            onSuccess = OnSuccess,
            onFailure = onFailure
        )
    }

    override fun getMoments(
        onSuccess: (moments: List<MomentVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mApi.getMoments(onSuccess, onFailure)
    }

    override fun getOTP(OnSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mApi.getOTP(OnSuccess, onFailure)
    }

    override fun getUserData(onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit) {
        mApi.getUserData(onSuccess, onFailure)
    }

    override fun editUserProfile(user: UserVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        Log.d("useredittest1", user.id)
        mApi.addUser(
            user.name,
            user.id,
            user.phone,
            user.dateOfBirth,
            user.gender,
            user.profilePhoto,
            onSuccess,
            onFailure
        )
    }

    override fun updateUserPhoto(user: UserVO, profilePhoto: Uri) {
        mApi.updateUserPhoto(user, profilePhoto)
    }

    override fun getContacts(onSuccess: (List<UserVO>) -> Unit, onFailure: (String) -> Unit) {
        mApi.getContacts(onSuccess, onFailure)
    }

    override fun getMessages(
        userId: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mRealTime.getMessages(userId,onSuccess,onFailure)
    }

    override fun sendNewMessage(
        message: MessageVO,
        receiverId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mRealTime.sendNewMessage(message, receiverId, onSuccess, onFailure)
    }

    override fun createNewMoment(
        images: List<Uri>,
        description: String,
        isVideo: Boolean,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mApi.createNewMoment(images, description, isVideo, onSuccess, onFailure)
    }

    override fun addContacts(
        contactId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mApi.AddContacts(contactId)
    }
}