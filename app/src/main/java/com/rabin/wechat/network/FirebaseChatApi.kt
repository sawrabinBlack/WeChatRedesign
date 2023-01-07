package com.rabin.wechat.network

import com.rabin.wechat.data.VOs.MessageVO

interface FirebaseChatApi {
    fun getMessages(userId:String, onSuccess:(List<MessageVO>)->Unit, onFailure :(String)->Unit)
    fun sendNewMessage(message:MessageVO,receiverId: String,onSuccess:()->Unit, onFailure :(String)->Unit)
}