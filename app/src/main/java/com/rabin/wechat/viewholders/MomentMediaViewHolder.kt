package com.rabin.wechat.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_moment_media.view.*

class MomentMediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(imageUrl: String) {

        Glide.with(itemView.context).load(imageUrl).into(itemView.ivMomentMedia)
    }
}