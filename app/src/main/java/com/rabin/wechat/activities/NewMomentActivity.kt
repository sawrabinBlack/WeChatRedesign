package com.rabin.wechat.activities

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telecom.VideoProfile.isVideo
import android.util.Log
import android.webkit.MimeTypeMap
import android.webkit.MimeTypeMap.getFileExtensionFromUrl
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.rabin.wechat.R
import com.rabin.wechat.adapters.NewMomentPhotoAdapter
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.data.models.WechatModel
import com.rabin.wechat.data.models.WechatModelImpl
import com.rabin.wechat.fragments.ProfileFragment.Companion.PICK_IMG
import com.rabin.wechat.mvp.presenters.NewMomentPresenter
import com.rabin.wechat.mvp.presenters.impls.NewMomentPresenterImpl
import com.rabin.wechat.mvp.views.NewMomentView
import com.rabin.wechat.network.CloudFirestoreDatabaseApiImpl
import com.rabin.wechat.network.FirebaseApi
import kotlinx.android.synthetic.main.activity_new_moment.*
import java.io.File


class NewMomentActivity : AppCompatActivity(), NewMomentView {

    val mApi: FirebaseApi = CloudFirestoreDatabaseApiImpl
    lateinit var mPresenter: NewMomentPresenter
    lateinit var mAdapter: NewMomentPhotoAdapter
    private val PICK_IMG = 1
    private val ImageList: ArrayList<Uri> = ArrayList<Uri>()
    private var isVideo = false

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NewMomentActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_moment)
        setUpPresenter()
        setUpRecyclerView()
        mPresenter.onUiReady(this)
        setUpOnClickListener()
    }

    private fun setUpOnClickListener() {
        btnAddPhoto.setOnClickListener {
            mPresenter.onTapAddMedia()
        }

        btnCreate.setOnClickListener {
            mPresenter.onTapCreateMoment(ImageList, etNewMomentDescription.text.toString(), isVideo)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = NewMomentPhotoAdapter()
        rvNewMoment.adapter = mAdapter
        rvNewMoment.layoutManager = GridLayoutManager(this, 3)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(NewMomentPresenterImpl::class.java)
        mPresenter.iniView(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMG) {
            if (resultCode == RESULT_OK) {
                val count: Int = data?.clipData?.itemCount ?: 0
                var CurrentImageSelect = 0
                if (count == 0) {
                    data?.data?.let {
                        val fileType = getMimeType(this, it)
                        if (fileType == "mp4") {
                            isVideo = true
                            Glide.with(this).load(it).thumbnail(0.1f).into(ivVideoNewMoment)
                        } else {

                        }

                        ImageList.add(it)
                    }
                }
                while (CurrentImageSelect < count) {
                    data?.clipData?.getItemAt(CurrentImageSelect)?.uri?.let {
                        ImageList.add(it)
                    }

                    CurrentImageSelect += 1
                }

                mAdapter.setUpData(ImageList)


            }
        }
    }


    fun getMimeType(context: Context, uri: Uri): String {

        if (uri.scheme.equals(ContentResolver.SCHEME_CONTENT)) {
            var mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(context.contentResolver.getType(uri)) ?: ""
        } else {
            return MimeTypeMap.getFileExtensionFromUrl(
                Uri.fromFile(File(uri.path.toString())).toString()
            )

        }
    }

    override fun showUserData(user: UserVO) {
        Glide.with(this).load(user.profilePhoto).into(ivProfilePhotoNewMoment)
        tvUserNameNewMoment.text = user.name
    }

    override fun openVideoAndImageSelect() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, PICK_IMG)
    }

    override fun endSection() {
        finish()
    }

    override fun showError(error: String) {

    }


}