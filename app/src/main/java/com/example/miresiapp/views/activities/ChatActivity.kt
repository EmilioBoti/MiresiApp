package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.businessLogic.chat.ChatLogicImpl
import com.example.miresiapp.utils.toast
import com.example.miresiapp.businessLogic.chat.IChat.ChatViewPresenter
import com.example.miresiapp.models.User
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity(), ChatViewPresenter {
    private lateinit var chatContainer: RecyclerView
    private var idUser: Int? = null
    private lateinit var model: ChatDataProvider
    private lateinit var chatLogicImpl: ChatLogicImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        idUser = intent?.extras?.getInt("id")
    }

    override fun onStart() {
        super.onStart()

        chatContainer = findViewById(R.id.chatContainer)
        model = ChatDataProvider()
        chatLogicImpl = ChatLogicImpl(this, model)
        idUser?.let {
            toast(this, it)
            lifecycleScope.launch {
                chatLogicImpl.requestChats(it)
            }
        }
    }

    override fun showChats(listChats: MutableList<User>?) {
        toast(this, listChats?.get(0)?.name)
    }

    override fun error(err: String) {
        toast(this, err)
    }
}