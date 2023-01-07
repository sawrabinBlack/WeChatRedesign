package com.rabin.wechat.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabin.wechat.R
import com.rabin.wechat.activities.ChatActivity
import com.rabin.wechat.activities.QRScanActivity
import com.rabin.wechat.adapters.ContactsAdapter
import com.rabin.wechat.adapters.GroupsAdapter
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment() {

    lateinit var mContactsAdapter: ContactsAdapter
    lateinit var mGroupsAdapter: GroupsAdapter
    lateinit var mContext: Context
    var mModel: WechatModel = WechatModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContactsAdapter = ContactsAdapter()
        mGroupsAdapter = GroupsAdapter()
        rvContacts.adapter = mContactsAdapter
        rvContacts.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        rvGroups.adapter = mGroupsAdapter
        rvGroups.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        btnAddContacts.setOnClickListener {
            startActivity(QRScanActivity.newIntent(mContext))
        }
        btnAddGroup.setOnClickListener {
            startActivity(Intent(mContext,ChatActivity::class.java))
        }
        mModel.getContacts(onSuccess = {
            mContactsAdapter.setNewData(it)
        }, onFailure = {

        })
    }


}