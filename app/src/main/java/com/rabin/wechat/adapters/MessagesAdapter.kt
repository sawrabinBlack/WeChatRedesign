package com.rabin.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.R
import com.rabin.wechat.data.VOs.MessageVO
import com.rabin.wechat.viewholders.MessagesViewHolder

class MessagesAdapter : RecyclerView.Adapter<MessagesViewHolder>() {
    var mList: List<MessageVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_chat_component, parent, false)
        return MessagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.bindData(mList[position])
        }
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun setUpData(data: List<MessageVO>) {
        mList = data
        notifyDataSetChanged()

    }
}