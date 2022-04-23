package com.example.miresiapp.businessLogic.registe

import com.example.miresiapp.models.RegisterUser
import com.example.miresiapp.businessLogic.registe.ISignUp.*


class SignUpBusinessLogic(private val viewer: ViewPresenter, private val model: ModelPresenter): Presenter {

    override suspend fun resgisterUser(newUser: RegisterUser) {
        val newU = model.resgister(newUser)
        newU?.let {
            viewer.isCreated(it)
        }
    }
}