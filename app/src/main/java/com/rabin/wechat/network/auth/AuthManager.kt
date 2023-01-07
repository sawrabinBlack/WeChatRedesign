package com.rabin.wechat.network.auth

import com.rabin.wechat.data.VOs.UserVO

interface AuthManager {

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun register(user:UserVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getUserName() : String
}