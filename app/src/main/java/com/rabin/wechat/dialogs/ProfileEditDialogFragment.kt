package com.rabin.wechat.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.rabin.wechat.R
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.mvp.presenters.ProfilePresenter
import com.rabin.wechat.mvp.presenters.impls.ProfilePresenterImpl
import kotlinx.android.synthetic.main.dialog_profile_edit.*
import kotlinx.android.synthetic.main.dialog_profile_edit.view.*

class ProfileEditDialogFragment : DialogFragment() {
    var mGender = "Male"
    lateinit var mPresenter: ProfilePresenter

    companion object {
        const val TAG_EDIT_PROFILE_DIALOG = "TAG_ADD_GROCERY_DIALOG"
        const val BUNDLE_NAME = "BUNDLE_NAME"
        const val BUNDLE_PROFILE_PHOTO = "BUNDLE_PROFILE_PHOTO"
        const val BUNDLE_ID = "BUNDLE_ID"
        const val BUNDLE_GENDER = "BUNDLE_GENDER"
        const val BUNDLE_DATE_OF_BIRTH = "BUNDLE_DATE_OF_BIRTH"
        const val BUNDLE_PHONE = "BUNDLE_PHONE"

        fun newFragment(): ProfileEditDialogFragment {
            return ProfileEditDialogFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        view.etUserNameEdit?.setText(arguments?.getString(BUNDLE_NAME))
        btnCancelProfileEdit.setOnClickListener {
            dismiss()
        }
        rgGenderEdit.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkId ->

            when (checkId) {
                R.id.rbMaleEdit -> mGender = "Male"
                R.id.rbFemaleEdit -> mGender = "Female"
                R.id.rbOtherEdit -> mGender = "Other"
            }
        }

        )
        btnSaveProfileEdit.setOnClickListener {
            val user = UserVO()
            user.name = etUserNameEdit.text.toString()
            user.gender = mGender
            user.id = arguments?.getString(BUNDLE_ID) ?: ""

            Log.d("useredittest", user.id)
            user.profilePhoto = arguments?.getString(BUNDLE_PROFILE_PHOTO) ?: ""
            user.phone = arguments?.getString(BUNDLE_PHONE) ?: ""
            user.dateOfBirth =
                "${spDateEdit.selectedItem}/${spMonthEdit.selectedItem}/${spYearEdit.selectedItem}"
            mPresenter.onTapSaveProfile(user)
            dismiss()
        }
    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(ProfilePresenterImpl::class.java)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_profile_edit, container, false)
        return view
    }
}