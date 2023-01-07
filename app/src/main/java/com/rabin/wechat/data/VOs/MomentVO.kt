package com.rabin.wechat.data.VOs

import android.provider.MediaStore
import com.google.android.gms.common.internal.FallbackServiceBroker

data class MomentVO(

    var ProfileImageUrl: String? = "",
    var description: String? = "",
    var momentImages: List<String>? = listOf(),
    var userName: String? = "",
    var createdTime: Long? = 0L,
    var isVideo: Boolean? = false

) {
}