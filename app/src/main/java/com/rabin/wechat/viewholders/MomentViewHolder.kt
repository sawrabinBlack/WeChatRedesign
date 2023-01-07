package com.rabin.wechat.viewholders

import android.opengl.Visibility
import android.text.format.DateUtils
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.firebase.crashlytics.internal.common.SystemCurrentTimeProvider
import com.rabin.wechat.adapters.MomentMediaAdapter
import com.rabin.wechat.data.VOs.MomentVO
import kotlinx.android.synthetic.main.view_holder_moment.view.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MomentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var mAdapter: MomentMediaAdapter

    init {

    }

    fun bindData(data: MomentVO) {
        mAdapter= MomentMediaAdapter()
        itemView.tvUserName.text =data.userName
        mAdapter.setUpData(data.momentImages?: listOf())
        itemView.tvDescriptionMoments.text = data.description
        itemView.rvMomentPhoto.adapter = mAdapter
        itemView.rvMomentPhoto.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

        itemView.tvMomentTime.text =  DateUtils.getRelativeTimeSpanString(data.createdTime?:0L)
        itemView.tvUserName.text = data.userName
        Glide.with(itemView.context).load(data.ProfileImageUrl).into(itemView.ivProfileMoment)
        if(data.isVideo==true) {
            itemView.rvMomentPhoto.visibility = View.GONE
            itemView.vvMoment.visibility = View.VISIBLE
            setUpPlayer(
                videoLink = data.momentImages?.firstOrNull()?:"",
                itemView
            )
        } else {itemView.vvMoment.visibility = View.GONE
            itemView.rvMomentPhoto.visibility = View.VISIBLE}

    }


    private fun setUpPlayer(videoLink: String, itemView: View) {
        val mediaDataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(itemView.context)

        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(videoLink))

        val mediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

        val simpleExoPlayer = ExoPlayer.Builder(itemView.context)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()

        simpleExoPlayer.addMediaSource(mediaSource)

        simpleExoPlayer.playWhenReady = true


        itemView.vvMoment.player = simpleExoPlayer
        itemView.vvMoment.requestFocus()
    }
}