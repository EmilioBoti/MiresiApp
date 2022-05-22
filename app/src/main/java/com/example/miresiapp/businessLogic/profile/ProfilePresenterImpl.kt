package com.example.miresiapp.businessLogic.profile

import com.example.miresiapp.businessLogic.chat.IChat
import com.example.miresiapp.businessLogic.login.ILogin
import com.example.miresiapp.models.User

class ProfilePresenterImpl(private val viewer: ILogin.PresenterView, private val model: IChat.ChatModelPresenter): IChat.ChatPresenter {
    private var user: MutableList<User>? = null

    override suspend fun requestChats(userId: Int) {
        user = model.getUser(userId)
        user?.let {
            viewer.login(it[0])
        }
    }

    override fun getList(): MutableList<User>? {
        TODO("Not yet implemented")
    }

    override fun findChats(toFind: String) {
        TODO("Not yet implemented")
    }

    override fun onChat(pos: Int) {
        TODO("Not yet implemented")
    }
}