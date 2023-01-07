package com.rabin.wechat.data.VOs

data class UserVO(
    var id: String = "",
    var name: String = "",
    var dateOfBirth: String = "",
    var phone: String = "",
    var gender: String = "",
    var profilePhoto: String = "",
    var password: String = ""
) {
}