package com.rabin.wechat.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabin.wechat.R
import com.rabin.wechat.viewholders.BaseViewHolder
import com.rabin.wechat.viewholders.NewMomentPhotoViewHolder

class NewMomentPhotoAdapter : RecyclerView.Adapter<NewMomentPhotoViewHolder>() {
    var mList: ArrayList<Uri> = ArrayList()
    val VIEW_TYPE_PHOTO = 2244
    val VIEW_TYPE_ADD = 1122
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMomentPhotoViewHolder {

//
//        when (viewType) {
//            VIEW_TYPE_PHOTO -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.view_holder_new_moment_photo, parent, false)
//                return BaseViewHolder(view)
//            }
//            VIEW_TYPE_ADD -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.view_holder_add_photo, parent, false)
//                return BaseViewHolder(view)
//            }
//            else -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.view_holder_new_moment_photo, parent, false)
//                return BaseViewHolder(view)
//            }
//        }
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_new_moment_photo, parent, false)
                return NewMomentPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewMomentPhotoViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.bindData(mList[position])
        }
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun setUpData(data: ArrayList<Uri>) {
        mList = data
        notifyDataSetChanged()

    }


//    override fun getItemViewType(position: Int): Int {
//        when (position) {
//            mList.count() -> {
//                return VIEW_TYPE_ADD
//            }
//            else -> return VIEW_TYPE_PHOTO
//        }
//
//    }
}