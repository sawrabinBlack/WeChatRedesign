package com.rabin.wechat.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.R
import com.rabin.wechat.viewholders.ConversationViewHolder

class ConversationAdapter: RecyclerView.Adapter<ConversationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_conversation,parent,false)
        return  ConversationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}