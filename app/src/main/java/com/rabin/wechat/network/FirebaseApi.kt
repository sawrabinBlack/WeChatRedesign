package com.rabin.wechat.network

import android.net.Uri
import com.rabin.wechat.data.VOs.MomentVO
import com.rabin.wechat.data.VOs.UserVO

interface FirebaseApi {
    fun addUser(
        name: String,
        id: String,
        phone: String,
        dateOfBirth: String,
        gender: String,
        profilePhoto: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoments(
        onSuccess: (moments: List<MomentVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getOTP(
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createNewMoment(
        images: List<Uri>,
        description: String,
        isVideo: Boolean,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserData(onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit)
    fun updateUserPhoto(user: UserVO, ProfilePhoto : Uri)
    fun AddContacts(contactId:String)
    fun getContacts(onSuccess: (moments: List<UserVO>) -> Unit,
                    onFailure: (String) -> Unit)

}