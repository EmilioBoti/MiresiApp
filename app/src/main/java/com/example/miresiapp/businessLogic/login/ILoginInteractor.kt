package com.example.miresiapp.businessLogic.login

import com.example.miresiapp.models.User
import com.example.miresiapp.models.UserLogin

interface ILoginInteractor {
    interface PresenterView{
        fun login(user: User?)
        fun error()
    }
    interface Presenter{
       suspend fun validUser(userLogin: UserLogin)
    }
    interface ModelLogin{
        suspend fun valildUserData(user: UserLogin): User?
        suspend fun updateUserSocket(id: Int)
    }
}