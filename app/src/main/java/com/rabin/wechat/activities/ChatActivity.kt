package com.rabin.wechat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.inappmessaging.CommonTypesProto.DailyAnalyticsSummary
import com.rabin.wechat.R
import com.rabin.wechat.adapters.MessagesAdapter
import com.rabin.wechat.data.VOs.MessageVO
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    val mModel: WechatModel = WechatModelImpl
    lateinit var mMessageAdapter: MessagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        mMessageAdapter = MessagesAdapter()
        rvConversationChats.adapter = mMessageAdapter
        rvConversationChats.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mModel.getMessages("kPAIqseS47YqkSWgWtd9Z8e01923", onSuccess = {
            mMessageAdapter.setUpData(it)
        }, onFailure = {

        })

        btnSendMsg.setOnClickListener {
            val message = MessageVO()
            message.timestamp = System.currentTimeMillis()
            message.name = "Rabin"
            message.user_id = "kPAIqseS47YqkSWgWtd9Z8e01923"
            message.message = etMessages.text.toString()
            mModel.sendNewMessage(message,"kPAIqseS47YqkSWgWtd9Z8e01923",{},{})
        }
    }
}