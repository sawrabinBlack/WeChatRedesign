package com.rabin.wechat.network

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.rabin.wechat.data.VOs.MomentVO
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.utils.*
import java.util.*

object CloudFirestoreDatabaseApiImpl : FirebaseApi {
    val db = Firebase.firestore
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
    val mAuth = FirebaseAuth.getInstance()

    override fun addUser(
        name: String,
        id: String,
        phone: String,
        dateOfBirth: String,
        gender: String,
        profilePhoto: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val userMap = hashMapOf(
            "name" to name,
            "id" to id,
            "phone" to phone,
            "dateOfBirth" to dateOfBirth,
            "gender" to gender,
            USER_PROFILE_PHOTO to profilePhoto

        )
        Log.d("useredit", id)
        db.collection("Users").document(id).set(userMap)


    }

    override fun getMoments(
        onSuccess: (moments: List<MomentVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("Moments").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Check Connection")
            }
                ?: run {
                    var momentsList: MutableList<MomentVO> = mutableListOf()
                    val result = value?.documents ?: listOf()
                    for (document in result) {
                        val data = document.data
                        val moment = MomentVO()
                        moment.userName = data?.get("userName") as String
                        moment.ProfileImageUrl = data?.get("ProfileImageUrl") as String?
                        moment.description = data?.get("description") as String
                        moment.momentImages = data.get("momentImages") as List<String>?
                        moment.isVideo = data?.get("isVideo") as Boolean?
                        moment.createdTime = data.get("createdTime") as Long
                        momentsList.add(moment)
                        Log.d("moment", moment.isVideo.toString())

                    }
                    momentsList.sortByDescending { it -> it.createdTime }
                    onSuccess(momentsList)
                }
        }
    }

    override fun getOTP(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        db.collection("Otp").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Check Connection")
            } ?: run {
                val result = value?.documents?.firstOrNull()?.data?.get("OTP") as String

                onSuccess(result)
            }
        }
    }

    override fun createNewMoment(
        images: List<Uri>,
        description: String,
        isVideo: Boolean,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        var imageLinks: MutableList<String> = mutableListOf()
        var id = ""
        val momentsMap = hashMapOf(
            "description" to description,
            "userName" to mAuth.currentUser?.displayName,
            "userID" to mAuth.currentUser?.uid,
            "createdTime" to System.currentTimeMillis(),
            "ProfileImageUrl" to mAuth.currentUser?.photoUrl,
            "momentImages" to imageLinks,
            "isVideo" to isVideo

        )

        db.collection("Moments").add(momentsMap).addOnCompleteListener {
            momentsMap["id"] = it.result.id
            id = it.result.id
            db.collection("Moments").document(it.result.id).set(momentsMap)
        }
        images.forEach { it ->
            val imageRef = storageRef.child("testImages/${UUID.randomUUID()}")
            val uploadTask = imageRef.putFile(it)
            val url = uploadTask.continueWithTask {
                return@continueWithTask imageRef.downloadUrl
            }.addOnCompleteListener {
                imageLinks.add(it.result?.toString() ?: "")
                db.collection("Moments").document(id).update("momentImages", imageLinks)
            }
        }



        onSuccess()
    }

    override fun getUserData(onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit) {
        db.collection("Users").document(mAuth.uid ?: "").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Check Connection")
            } ?: run {
                val data = value?.data
                val user = UserVO()

                user.id = data?.get(USER_ID) as String
                user.dateOfBirth = data?.get(USER_DATE_OF_BIRTH) as String
                user.name = data?.get(USER_NAME) as String
                user.profilePhoto = data?.get(USER_PROFILE_PHOTO) as String
                user.phone = data?.get(USER_PHONE) as String
                user.gender = data?.get(USER_GENDER) as String
                onSuccess(user)
            }


        }
    }

    override fun updateUserPhoto(user: UserVO, ProfilePhoto: Uri) {
        val imageRef = storageRef.child("testImages/${UUID.randomUUID()}")
        val uploadTask = imageRef.putFile(ProfilePhoto)
        val url = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener {
            addUser(
                name = user.name,
                id = user.id,
                phone = user.phone,
                dateOfBirth = user.dateOfBirth,
                user.gender,
                profilePhoto = it.result.toString(),
                {},
                {}
            )

            mAuth.currentUser?.updateProfile(
                UserProfileChangeRequest.Builder().setPhotoUri(it.result).build()
            )
        }
    }

    override fun AddContacts(contactId: String) {
        db.collection("Users").document(contactId).addSnapshotListener { value, error ->
            error?.let {
            } ?: run {
                val data = value?.data
                val user = UserVO()

                val userMap = hashMapOf(
                    "name" to data?.get(USER_NAME) as String,
                    "id" to data?.get(USER_ID) as String,
                    "phone" to data?.get(USER_PHONE) as String,
                    "dateOfBirth" to data?.get(USER_DATE_OF_BIRTH) as String,
                    "gender" to data?.get(USER_GENDER) as String,
                    USER_PROFILE_PHOTO to data?.get(USER_PROFILE_PHOTO) as String

                )
                db.collection("Users").document(mAuth.uid ?: "").collection("Contacts")
                    .document(contactId).set(userMap)

            }


            db.collection("Users").document(mAuth.uid ?: "").addSnapshotListener { value, error ->
                error?.let {
                } ?: run {
                    val data = value?.data
                    val user = UserVO()

                    val userMap = hashMapOf(
                        "name" to data?.get(USER_NAME) as String,
                        "id" to data?.get(USER_ID) as String,
                        "phone" to data?.get(USER_PHONE) as String,
                        "dateOfBirth" to data?.get(USER_DATE_OF_BIRTH) as String,
                        "gender" to data?.get(USER_GENDER) as String,
                        USER_PROFILE_PHOTO to data?.get(USER_PROFILE_PHOTO) as String

                    )

                    db.collection("Users").document(contactId).collection("Contacts")
                        .document(mAuth.uid ?: "").set(userMap)
                }


            }
        }
    }

    override fun getContacts(
        onSuccess: (moments: List<UserVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        Log.d("contacts", mAuth.uid.toString())
        db.collection("Users").document(mAuth.uid ?: "").collection("Contacts")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Check Connection")

                }
                    ?: run {
                        var momentsList: MutableList<UserVO> = mutableListOf()
                        val result = value?.documents ?: listOf()
                        for (document in result) {
                            val data = document.data
                            val user = UserVO()

                            user.id = data?.get("id") as String
                            user.name = data?.get("name") as String
                            user.profilePhoto = data.get("profilePhoto") as String
                            momentsList.add(user)

                        }
                        Log.d("contacts", momentsList.toString())
                        momentsList.sortByDescending { it -> it.name }
                        onSuccess(momentsList)
                    }
            }
    }
}