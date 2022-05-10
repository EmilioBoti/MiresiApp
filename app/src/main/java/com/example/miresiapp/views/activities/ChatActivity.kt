package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
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
import com.example.miresiapp.utils.LocalData
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.socket.client.Socket
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity(), ChatViewPresenter, OnClickItemView {
    private var idUser: Int? = null
    private lateinit var chatContainer: RecyclerView
    private lateinit var searcher: SearchView
    private lateinit var model: ChatDataProvider
    private lateinit var chatLogicImpl: ChatLogicImpl
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var gson: Gson
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        idUser = intent?.extras?.getInt("id")

    }

    override fun onStart() {
        super.onStart()

        chatContainer = findViewById(R.id.chatContainer)
        searcher = findViewById(R.id.searcher)
        image = findViewById(R.id.imgUser)

        val img = LocalData.getImageUser(applicationContext)
        Picasso.get().load(img).fit().into(image)

        gson = Gson()
        model = ChatDataProvider()
        chatLogicImpl = ChatLogicImpl(this, model, applicationContext)

        idUser?.let {
            lifecycleScope.launch {
                chatLogicImpl.requestChats(it)
            }
        }

        searcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //chatLogicImpl.findChats(newText!!)
                return true
            }
        })
    }

    override fun showChats(listChats: MutableList<User>?) {
        listChats?.let {
            setChats(listChats)
        }
    }

    override fun chatFound(listChats: MutableList<User>) {
        setChats(listChats)
    }

    override fun chatTo(user: User) {
        idUser?.let {
            Intent(this, MessengerActivity::class.java).apply {
                putExtra("from", it)
                putExtra("to", user.id)
                putExtra("name", user.name)
                startActivity(this)
            }
        }
    }

    override fun error(err: String) {
        toast(this, err)
    }

    override fun onClickItem(pos: Int, view: View) {
        chatLogicImpl.onChat(pos)
    }
    private fun setChats(listChats: MutableList<User>){
        chatContainer.removeAllViews()
        chatAdapter = ChatAdapter(listChats, applicationContext, this)
        chatContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            //addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
            adapter = chatAdapter
        }
    }
    override fun addFavoriteItem(pos: Int, view: View) { }
}