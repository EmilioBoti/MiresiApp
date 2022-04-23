package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.SocketCon
import com.example.miresiapp.adapters.chatAdapter.ChatAdapter
import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.businessLogic.chat.ChatLogicImpl
import com.example.miresiapp.utils.toast
import com.example.miresiapp.businessLogic.chat.IChat.ChatViewPresenter
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.User
import com.google.gson.Gson
import io.socket.client.Socket
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity(), ChatViewPresenter, OnClickItemView {
    private var idUser: Int? = null
    private lateinit var chatContainer: RecyclerView
    private lateinit var model: ChatDataProvider
    private lateinit var chatLogicImpl: ChatLogicImpl
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var listChats: MutableList<User>
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        idUser = intent?.extras?.getInt("id")
    }

    override fun onStart() {
        super.onStart()

        chatContainer = findViewById(R.id.chatContainer)
        gson = Gson()
        model = ChatDataProvider()
        chatLogicImpl = ChatLogicImpl(this, model, applicationContext)

        idUser?.let {
            lifecycleScope.launch {
                chatLogicImpl.requestChats(it)
            }
        }
    }

    override fun showChats(listChats: MutableList<User>?) {
        this.listChats = listChats!!
        chatAdapter = ChatAdapter(this.listChats, applicationContext, this)

        chatContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            adapter = chatAdapter
        }
    }

    override fun error(err: String) {
        toast(this, err)
    }

    override fun onClickItem(pos: Int, view: View) {
        idUser?.let {
            Intent(this, MessengerActivity::class.java).apply {
                putExtra("from", it)
                putExtra("to", listChats[pos].id)
                putExtra("name", listChats[pos].name)
                startActivity(this)
            }
        }
    }

    override fun addFavoriteItem(pos: Int, view: View) { }
}