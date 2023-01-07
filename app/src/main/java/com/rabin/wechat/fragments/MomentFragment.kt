package com.rabin.wechat.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabin.wechat.R
import com.rabin.wechat.activities.NewMomentActivity
import com.rabin.wechat.adapters.MomentAdapter
import com.rabin.wechat.data.VOs.MomentVO
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.mvp.presenters.MomentPresenter
import com.rabin.wechat.mvp.presenters.impls.MomentPresenterImpl
import com.rabin.wechat.mvp.views.MomentView
import kotlinx.android.synthetic.main.fragment_moment.*


class MomentFragment : Fragment(),MomentView {
    lateinit var mPresenter:MomentPresenter
    lateinit var mContext: Context
    lateinit var mAdapter:MomentAdapter
    private var mModel:WechatModel=WechatModelImpl
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpPresenter()
        btnNewMoment.setOnClickListener {
            mPresenter.onTapNewMoment()
        }
        mPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mPresenter= ViewModelProvider(this)[MomentPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    private fun setUpRecyclerView() {
        mAdapter = MomentAdapter()
        rvMoment.adapter = mAdapter
        rvMoment.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moment, container, false)

    }

    override fun showMomentList(moments: List<MomentVO>) {
        mAdapter.setUpData(moments)
    }

    override fun navigateToNewMoment() {
        startActivity(NewMomentActivity.newIntent(mContext))
    }

}