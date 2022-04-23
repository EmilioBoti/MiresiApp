package com.example.miresiapp.businessLogic.registe

import com.example.miresiapp.models.RegisterUser
import com.example.miresiapp.models.User

interface ISignUp {
    interface ViewPresenter {
        fun isCreated(newUser: User)
    }
    interface Presenter{
        suspend fun resgisterUser(newUser: RegisterUser)
    }
    interface ModelPresenter{
        suspend fun resgister(newUser: RegisterUser): User?
    }
}