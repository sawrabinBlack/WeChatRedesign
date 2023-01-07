package com.rabin.wechat.data.VOs

import android.util.Log

data class MessageVO(
    var file: String = "",
    var message:String ="",
    var name:String="",
    var timestamp : Long = 0L,
    var user_id: String =""
)
