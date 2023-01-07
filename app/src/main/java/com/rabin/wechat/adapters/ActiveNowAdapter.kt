package com.rabin.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.R
import com.rabin.wechat.viewholders.ActiveUserViewHolder

class ActiveNowAdapter : RecyclerView.Adapter<ActiveUserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveUserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_hold_chat_contact, parent, false)
        return ActiveUserViewHolder(view)

    }

    override fun onBindViewHolder(holder: ActiveUserViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }

}