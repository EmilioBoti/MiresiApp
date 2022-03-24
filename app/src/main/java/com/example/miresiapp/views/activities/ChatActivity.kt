package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miresiapp.R
import com.example.miresiapp.utils.toast

class ChatActivity : AppCompatActivity() {
    private var idUser: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


    }

    override fun onStart() {
        super.onStart()

        idUser?.let {
            toast(this, idUser)
        }
    }
}