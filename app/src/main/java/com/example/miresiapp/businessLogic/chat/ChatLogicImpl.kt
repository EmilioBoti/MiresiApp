package com.example.miresiapp.businessLogic.chat

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.miresiapp.R
import com.example.miresiapp.SocketCon
import com.example.miresiapp.businessLogic.chat.IChat.ChatViewPresenter
import com.example.miresiapp.businessLogic.chat.IChat.ChatPresenter
import com.example.miresiapp.models.Message
import com.example.miresiapp.models.User
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.Notifications.Companion.createNotificationChannel
import com.example.miresiapp.utils.Notifications.Companion.showNotifycationMusic
import com.google.gson.Gson
import io.socket.client.Socket

class ChatLogicImpl(private val viewer: ChatViewPresenter, private val model: ChatDataProvider, private val context: Context ): ChatPresenter {
    private var listUser: MutableList<User>? = null
    private val mSocket: Socket = SocketCon.getSocket()
    private val gson: Gson = Gson()
    private lateinit var message: Message
    private var userId: Int? = null

    init {
        createNotificationChannel(context)
        mSocket.on("private", ) { data ->
            val d = data[0]
            message = gson.fromJson<Message>(d.toString(), Message::class.java)
            userId = LocalData.getCurrentUserId(context)
            if (userId != message.userSenderId) showNotifycationMusic(context,message)
        }
    }

    override suspend fun requestChats(userId: Int) {
        listUser =  model.getChats(userId)

        listUser?.let {
            viewer.showChats(it)
        } ?: viewer.error("Error")
    }

    override fun getList(): MutableList<User>? = listUser

    override fun findChats(toFind: String) {
        val list: MutableList<User> = mutableListOf()
        listUser?.forEach {
            if (it.name.lowercase().startsWith(toFind.lowercase())){
               list.add(it)
            }
        }
        viewer.chatFound(list)
    }

    override fun onChat(pos: Int) {
        listUser?.let {
            viewer.chatTo(it[pos])
        }
    }
}