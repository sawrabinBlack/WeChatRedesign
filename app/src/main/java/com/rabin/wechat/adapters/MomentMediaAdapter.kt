package com.rabin.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.R
import com.rabin.wechat.data.VOs.MomentVO
import com.rabin.wechat.viewholders.MomentMediaViewHolder

class MomentMediaAdapter : RecyclerView.Adapter<MomentMediaViewHolder>() {
    var mList: List<String> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentMediaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_moment_media, parent, false)
        return MomentMediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MomentMediaViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.bindData(mList[position])
        }
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun setUpData(data: List<String>) {
        mList = data
        notifyDataSetChanged()

    }
}