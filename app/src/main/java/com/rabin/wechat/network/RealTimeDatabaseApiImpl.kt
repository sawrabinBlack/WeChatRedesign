package com.rabin.wechat.network

import android.os.Message
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rabin.wechat.data.VOs.MessageVO
import com.rabin.wechat.utils.CONTACTS_MESSAGES

object RealTimeDatabaseApiImpl : FirebaseChatApi {
    private val database: DatabaseReference = Firebase.database.reference
    private val mAuth = FirebaseAuth.getInstance()
    override fun getMessages(
        userId: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        Log.d("Message", "ok")
        Log.d("firebase", mAuth.uid ?: "")
        database.child(CONTACTS_MESSAGES).child(mAuth.uid ?: "").child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("Message", "okSetData")
                    val messageList = arrayListOf<MessageVO>()
                    snapshot.children.forEach { dataSnapshot ->
                        dataSnapshot.getValue(MessageVO::class.java)?.let {
                            messageList.add(it)
                        }
                        Log.d("firebase", dataSnapshot.toString())

                    }
                    onSuccess(messageList)
                    Log.d("Message", messageList.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Message", error.message.toString())
                }

            })
    }

    override fun sendNewMessage(
        message: MessageVO,
        receiverId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child(CONTACTS_MESSAGES).child(mAuth.uid ?: "").child(receiverId)
            .child(message.timestamp.toString()).setValue(message)
        database.child(CONTACTS_MESSAGES).child(receiverId).child(mAuth.uid ?: "")
            .child(message.timestamp.toString()).setValue(message)
    }
}