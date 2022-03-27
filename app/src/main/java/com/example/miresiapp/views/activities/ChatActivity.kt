package com.example.miresiapp.views.activities

import android.content.Context
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
import com.example.miresiapp.models.Message
import com.example.miresiapp.models.User
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity(), ChatViewPresenter, OnClickItemView {
    private var idUser: Int? = null
    private lateinit var chatContainer: RecyclerView
    private lateinit var model: ChatDataProvider
    private lateinit var chatLogicImpl: ChatLogicImpl
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var listChats: MutableList<User>
    private lateinit var mSocket: Socket
    private val data: MutableMap<String, *>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        idUser = intent?.extras?.getInt("id")
    }

    override fun onStart() {
        super.onStart()

        chatContainer = findViewById(R.id.chatContainer)
        SocketCon.setSocket()
        mSocket = SocketCon.getSocket()
        getUserLoged()
        model = ChatDataProvider()
        chatLogicImpl = ChatLogicImpl(this, model)

        idUser?.let {
            toast(applicationContext, it)
            lifecycleScope.launch {
                chatLogicImpl.requestChats(it)
            }
        }

        mSocket.on("private"){ data->
            val da = data[0]
        }
    }

    private fun getUserLoged(){
        mSocket.connect()
        mSocket.on("connect") {
            conn()
        }
    }
    private fun conn(){
        val prefe = getSharedPreferences(resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
        val data: MutableMap<String, *>? = prefe?.all

        data?.let {
            mSocket.emit("user", it["userId"], mSocket.id())
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

    override fun onClickItem(pos: Int) {
        idUser?.let {
            Intent(this, MessengerActivity::class.java).apply {
                putExtra("senderUserId", it)
                putExtra("receiverUserId", listChats[pos].id)
                putExtra("name", listChats[pos].name)
                putExtra("receiverSOId", listChats[0].socketId)
                startActivity(this)
            }
        }
    }

    override fun addFavoriteItem(pos: Int, view: View) {
    }
}