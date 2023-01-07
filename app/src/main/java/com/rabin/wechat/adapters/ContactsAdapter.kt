package com.rabin.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User
import com.rabin.wechat.R
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.viewholders.ContactsViewHolder

class ContactsAdapter : RecyclerView.Adapter<ContactsViewHolder>() {
    var mList: List<UserVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_contacts, parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.bindData(mList[position])
        }
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(contacts: List<UserVO>) {
        mList = contacts
        notifyDataSetChanged()
    }

}