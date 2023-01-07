package com.rabin.wechat.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabin.wechat.R
import com.rabin.wechat.adapters.ActiveNowAdapter
import com.rabin.wechat.adapters.ConversationAdapter
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment() {

lateinit var mContext: Context
lateinit var mActiveAdapter:ActiveNowAdapter
lateinit var mConversationAdapter:ConversationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mActiveAdapter= ActiveNowAdapter()

        mConversationAdapter = ConversationAdapter()
        rvConversation.adapter=mConversationAdapter
        rvConversation.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rvActiveNow.adapter = mActiveAdapter
        rvActiveNow.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)

        super.onViewCreated(view, savedInstanceState)
    }

}