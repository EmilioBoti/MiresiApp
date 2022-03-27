package com.example.miresiapp.businessLogic.message

import com.example.miresiapp.models.Message

interface IMessenger {
    interface ViewPresenter{
        fun showMessage(listMessage: MutableList<Message>)
        fun updateChat(message: Message)
        fun error(err: String)
    }
    interface Presenter{
        suspend fun requestMessage(from: Int, to: Int)
    }
}