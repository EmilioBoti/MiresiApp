package com.example.miresiapp.businessLogic.message

import com.example.miresiapp.models.Message
import com.example.miresiapp.models.MessageModel

interface IMessenger {
    interface ViewPresenter{
        fun showMessage(listMessage: MutableList<Message>)
        fun updateChat(message: Message, pos: Int)
        fun error(err: String)
    }
    interface Presenter{
        suspend fun requestMessage(from: Int, to: Int)
        fun sendMessage(message: MessageModel)
    }
}