package com.rabin.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.R
import com.rabin.wechat.viewholders.GroupsViewHolder

class GroupsAdapter:RecyclerView.Adapter<GroupsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.view_holder_group,parent,false)
        return GroupsViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return  3
    }
}