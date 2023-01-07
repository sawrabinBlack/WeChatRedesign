package com.rabin.wechat.viewholders

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_moment_media.view.*
import kotlinx.android.synthetic.main.view_holder_new_moment_photo.view.*

class NewMomentPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(data: Uri){
        itemView.ivNewMomentImage.setImageURI(data)
    }
}