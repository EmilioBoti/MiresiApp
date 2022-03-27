package com.example.miresiapp.businessLogic.chat

import com.example.miresiapp.businessLogic.chat.IChat.ChatViewPresenter
import com.example.miresiapp.businessLogic.chat.IChat.ChatPresenter
import com.example.miresiapp.models.User

class ChatLogicImpl(private val viewer: ChatViewPresenter, private val model: ChatDataProvider ): ChatPresenter {
    private var listUser: MutableList<User>? = null

    override suspend fun requestChats(userId: Int) {
        listUser =  model.getChats(userId)

        listUser?.let {
            viewer.showChats(it)
        } ?: viewer.error("Error")
    }

    override fun getList(): MutableList<User>? = listUser
}