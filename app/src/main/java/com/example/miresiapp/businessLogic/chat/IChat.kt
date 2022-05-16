package com.example.miresiapp.businessLogic.chat

import com.example.miresiapp.models.Message
import com.example.miresiapp.models.User

interface IChat {
    interface ChatViewPresenter{
        fun showChats(listChats: MutableList<User>?)
        fun chatFound(listChats: MutableList<User>)
        fun chatTo(user: User)
        fun error(err: String)
    }
    interface ChatPresenter{
        suspend fun requestChats(userId: Int)
        fun getList(): MutableList<User>?
        fun findChats(toFind: String)
        fun onChat(pos: Int)
    }
    interface  ChatModelPresenter{
        suspend fun getChats(userId: Int): MutableList<User>?
        suspend fun getUser(userId: Int): MutableList<User>?
        suspend fun getMessages(from: Int, to: Int): MutableList<Message>?
    }
}