package com.example.miresiapp.businessLogic.message

import com.example.miresiapp.SocketCon
import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.models.Message
import com.google.gson.Gson
import io.socket.client.Socket

class MessengeLogicImpl(private val viewer: IMessenger.ViewPresenter, private val model: ChatDataProvider): IMessenger.Presenter {
    private var message: Message? = null
    private var listMessage: MutableList<Message>? = null
    private var mSocket: Socket
    private var gson: Gson

    init {
        mSocket = SocketCon.getSocket()
        gson = Gson()
        socketEventsListenner()
    }

    override suspend fun requestMessage(from: Int, to: Int) {
        listMessage = model.getMessages(from, to)

        listMessage?.let {
            viewer.showMessage(it)
        }
    }

    private fun socketEventsListenner(){
        mSocket.on("private", ){ data ->
            val d = data[0]
            message = gson.fromJson<Message>(d.toString(), Message::class.java)
            viewer.updateChat(message!!)
        }
    }
}