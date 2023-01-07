package com.rabin.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.R
import com.rabin.wechat.data.VOs.MomentVO
import com.rabin.wechat.viewholders.MomentViewHolder

class MomentAdapter : RecyclerView.Adapter<MomentViewHolder>() {
    var mList: List<MomentVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_moment, parent, false)
        return MomentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MomentViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.bindData(mList[position])
        }
    }

    override fun getItemCount(): Int {
      return mList.count()

    }

    fun setUpData(data: List<MomentVO>) {
        mList = data
        notifyDataSetChanged()

    }
}
