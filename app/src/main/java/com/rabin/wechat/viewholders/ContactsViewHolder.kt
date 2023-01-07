package com.rabin.wechat.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rabin.wechat.data.VOs.UserVO
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import kotlinx.android.synthetic.main.view_hold_chat_contact.view.*
import kotlinx.android.synthetic.main.view_holder_contacts.view.*

class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(contact: UserVO) {
        itemView.tvContactsName.text = contact.name
        Glide.with(itemView.context).load(contact.profilePhoto?:"").into(itemView.ivContactsPhoto)
    }
}