package com.rabin.wechat.network.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.network.CloudFirestoreDatabaseApiImpl
import com.rabin.wechat.network.FirebaseApi

object FirebaseAuthManagerImpl : AuthManager {
    private val mApi: FirebaseApi = CloudFirestoreDatabaseApiImpl
    private val mFirebaseAuth = FirebaseAuth.getInstance()
    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                onSuccess()
            } else onFailure(
                it.exception?.message ?: " Check Your Connection"
            )
        }
    }

    override fun register(
        user: UserVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mFirebaseAuth.createUserWithEmailAndPassword("${user.phone}@gmail.com", user.password ?: "")
            .addOnCompleteListener {
                if (it.isSuccessful && it.isComplete) {
                    if (it.isSuccessful && it.isComplete) {
                        mFirebaseAuth.currentUser?.updateProfile(
                            UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                        )
                    }
                    mApi.addUser(
                        name = user.name ?: "",
                        id = mFirebaseAuth.currentUser?.uid ?: "",
                        phone = user.phone ?: "",
                        dateOfBirth = user.dateOfBirth ?: "",
                        gender = user.gender ?: "",
                        profilePhoto = "",
                        {

                        },
                        {

                        })
                    onSuccess()
                }
            }

    }

    override fun getUserName(): String {
        return "Hello"
    }
}