package com.example.miresiapp.businessLogic.message

import com.example.miresiapp.SocketCon
import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.models.Message
import com.example.miresiapp.models.MessageModel
import com.google.gson.Gson
import io.socket.client.Socket

class MessageLogicImpl(private val viewer: IMessage.ViewPresenter, private val model: ChatDataProvider): IMessage.Presenter {
    private var message: Message? = null
    private var listMessage: MutableList<Message>? = null
    private var mSocket: Socket = SocketCon.getSocket()
    private var gson: Gson = Gson()

    init {
        socketEventsListenner()
    }

    override suspend fun requestMessage(from: Int, to: Int) {
        listMessage = model.getMessages(from, to)

        listMessage?.let {
            viewer.showMessage(it)
        }
    }

    override fun sendMessage(message: MessageModel) {
        val msm: String = gson.toJson(message)
        mSocket.emit("message", msm)
    }

    private fun socketEventsListenner() {
        mSocket.on("private", ){ data ->
            val d = data[0]
            message = gson.fromJson<Message>(d.toString(), Message::class.java)
            listMessage?.add(message!!)
            listMessage?.let {
                viewer.updateChat(it[it.size -1], it.size -1)
            }
        }
    }
}