package com.rabin.wechat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.rabin.wechat.R
import com.rabin.wechat.data.VOs.UserVO
import com.rabin.wechat.mvp.presenters.FillUserDataPresenter
import com.rabin.wechat.mvp.presenters.impls.FillUserDataPresenterImpl
import com.rabin.wechat.mvp.views.FillUserDataView
import kotlinx.android.synthetic.main.activity_fill_user_data.*


class FillUserDataActivity : AppCompatActivity(), FillUserDataView {
    lateinit var mPresenter: FillUserDataPresenter
    private var gender = "Male"

    companion object {
        const val USER_EMAIL = "USER_EMAIL"
        fun newIntent(context: Context, data: String): Intent {
            val intent = Intent(context, FillUserDataActivity::class.java)
            intent.putExtra(USER_EMAIL, data)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_user_data)
        val data = intent.getStringExtra(USER_EMAIL)
        setUpPresenter()
        btnRegister.setOnClickListener {
            val user = UserVO()
            val dateOfBirth ="${spDate.selectedItem}/ ${spMonth.selectedItem}/${spYear.selectedItem}"
            user.name = etUserName.text.toString()
            user.gender= gender
            user.password=etPassword.text.toString()
            user.dateOfBirth = dateOfBirth
            user.phone=data?:""
            mPresenter.onTapRegister(
                user,
                etUserName.text.toString()
            )
        }

        rgGender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbMale -> gender = "Male"
                R.id.rbFemale -> gender = "Female"
                R.id.rbOther -> gender = "Other"
            }

            Log.d("gender", gender)


        }



    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[FillUserDataPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    override fun navigateToMainScreen() {
        startActivity(MainActivity.newIntent(this))
    }
}