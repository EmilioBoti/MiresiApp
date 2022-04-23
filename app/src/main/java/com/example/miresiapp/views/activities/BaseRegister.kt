package com.example.miresiapp.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miresiapp.R
import com.example.miresiapp.views.fragments.LoginFragment

class BaseRegister : AppCompatActivity() {
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        checkLogin()
    }
    private fun checkLogin(){
        userId = getCurrentUserData()

        userId?.let {
            if (it != 0){
                Intent(this, DashBoardActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            }else toLogin()
        } ?: toLogin()
    }
    private fun toLogin(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.loginRegisterContainer, LoginFragment())
            .commit()
    }
    private fun getCurrentUserData(): Int? {
        val prefe = getSharedPreferences(resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
        return prefe?.getInt("userId", 0)
    }
}