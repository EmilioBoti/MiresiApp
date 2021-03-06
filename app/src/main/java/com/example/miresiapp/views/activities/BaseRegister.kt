package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miresiapp.R
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.views.fragments.LoginFragment

class BaseRegister : AppCompatActivity() {
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.splashScreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        checkLogin()
    }
    private fun checkLogin(){
        userId = LocalData.getCurrentUserId(applicationContext)

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
}