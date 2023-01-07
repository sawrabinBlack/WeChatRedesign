package com.rabin.wechat.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.data.VOs.MessageVO
import kotlinx.android.synthetic.main.view_holder_chat_component.view.*

class MessagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(data: MessageVO) {
        itemView.tvMessageReceive.text = data.message
        itemView.tvMessageSender.text = data.message
    }
}