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
import com.google.gson.Gson
import io.socket.client.Socket

class ChatLogicImpl(private val viewer: ChatViewPresenter, private val model: ChatDataProvider, val context: Context ): ChatPresenter {
    private var listUser: MutableList<User>? = null
    private val mSocket: Socket = SocketCon.getSocket()
    private val gson: Gson = Gson()
    private lateinit var message: Message
    private val channelId = "com.example.miresiapp"
    private val notificationId: Int = 1

    init {
        createNotificationChannel()
        mSocket.on("private", ) { data ->
            val d = data[0]
            message = gson.fromJson<Message>(d.toString(), Message::class.java)
            showNotifycationMusic(message)
        }
    }

    private fun showNotifycationMusic(message: Message){
        val notificationBuider = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.chat_24)
            .setContentTitle(message.fromUser)
            .setContentText(message.sms)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(message.userSenderId, notificationBuider.build())
        }
    }

    private fun createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "com.example.musicapp"
            val descriptionText = "music notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            //register channel in the system
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override suspend fun requestChats(userId: Int) {
        listUser =  model.getChats(userId)

        listUser?.let {
            viewer.showChats(it)
        } ?: viewer.error("Error")
    }

    override fun getList(): MutableList<User>? = listUser
}