package com.rabin.wechat.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rabin.wechat.R
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.dialogs.ProfileEditDialogFragment
import com.rabin.wechat.dialogs.ProfileEditDialogFragment.Companion.BUNDLE_DATE_OF_BIRTH
import com.rabin.wechat.dialogs.ProfileEditDialogFragment.Companion.BUNDLE_GENDER
import com.rabin.wechat.dialogs.ProfileEditDialogFragment.Companion.BUNDLE_ID
import com.rabin.wechat.dialogs.ProfileEditDialogFragment.Companion.BUNDLE_NAME
import com.rabin.wechat.dialogs.ProfileEditDialogFragment.Companion.BUNDLE_PHONE
import com.rabin.wechat.dialogs.ProfileEditDialogFragment.Companion.BUNDLE_PROFILE_PHOTO
import com.rabin.wechat.dialogs.ProfileEditDialogFragment.Companion.TAG_EDIT_PROFILE_DIALOG
import com.rabin.wechat.dialogs.QRDialogFragment
import com.rabin.wechat.dialogs.QRDialogFragment.Companion.BUNDLE_USER_ID
import com.rabin.wechat.dialogs.QRDialogFragment.Companion.TAG_EDIT_QR_DIALOG
import com.rabin.wechat.mvp.presenters.ProfilePresenter
import com.rabin.wechat.mvp.presenters.impls.ProfilePresenterImpl
import com.rabin.wechat.mvp.views.ProfileView
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ProfileView {
    lateinit var mPresenter: ProfilePresenter
    var userId: String? = null

    companion object {
        const val PICK_IMG = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        mPresenter.onUiReady(this)
        btnUploadProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*";
            startActivityForResult(intent, PICK_IMG)

        }

        btnEditProfile.setOnClickListener {
            mPresenter.onTapEditProfile()

        }

        btnShowQR.setOnClickListener {
            val qrDialog = QRDialogFragment.newQRdialog()
            val bundle = Bundle()
            bundle.putString(BUNDLE_USER_ID, userId)
            qrDialog.arguments = bundle
            qrDialog.show(childFragmentManager, TAG_EDIT_QR_DIALOG)
            Log.d("userID",userId.toString())

        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ProfilePresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    override fun showProfile(userVO: UserVO) {
        userId = userVO.id
        tvUserNameProfile.text = userVO.name
        tvPhoneUserProfile.text = userVO.phone
        tvBirthDayProfile.text = userVO.dateOfBirth
        tvGenderProfile.text = userVO.gender
        Glide.with(this).load(userVO.profilePhoto).into(ivUserPhotoProfile)
    }

    override fun showEditDialog(user: UserVO) {
        val profileDialog = ProfileEditDialogFragment()
        val bundle = Bundle()
        Log.d("useredittest", user.id)
        bundle.putString(BUNDLE_ID, user.id)
        bundle.putString(BUNDLE_NAME, user.name)
        bundle.putString(BUNDLE_PHONE, user.phone)
        bundle.putString(BUNDLE_DATE_OF_BIRTH, user.dateOfBirth)
        bundle.putString(BUNDLE_GENDER, user.gender)
        bundle.putString(BUNDLE_PROFILE_PHOTO, user.profilePhoto)
        profileDialog.arguments = bundle

        profileDialog.show(childFragmentManager, TAG_EDIT_PROFILE_DIALOG)
    }

    override fun showError(error: String) {

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMG && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            data.data?.let {
                mPresenter.onPhotoTaken(it)
            }

        }
    }

}