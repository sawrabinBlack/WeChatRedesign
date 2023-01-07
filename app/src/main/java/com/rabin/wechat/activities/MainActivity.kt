package com.rabin.wechat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rabin.wechat.R
import com.rabin.wechat.fragments.ChatFragment
import com.rabin.wechat.fragments.ContactsFragment
import com.rabin.wechat.fragments.MomentFragment
import com.rabin.wechat.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(MomentFragment())
        setUpOnClickListeners()

    }

    private fun setUpOnClickListeners() {
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_moment -> switchFragment(MomentFragment())
                R.id.action_contacts -> switchFragment(ContactsFragment())
                R.id.action_chat-> switchFragment(ChatFragment())
                R.id.action_me-> switchFragment(ProfileFragment())
            }
            true
        }


    }

    private fun switchFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()

    }
}