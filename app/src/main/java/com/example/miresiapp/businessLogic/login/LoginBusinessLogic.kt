package com.example.miresiapp.businessLogic.login

import com.example.miresiapp.businessLogic.login.ILoginInteractor.PresenterView
import com.example.miresiapp.businessLogic.login.ILoginInteractor.Presenter
import com.example.miresiapp.models.DataProvider
import com.example.miresiapp.models.User
import com.example.miresiapp.models.UserLogin

class LoginBusinessLogic(private val interactorView: PresenterView, private val model: DataProvider): Presenter  {

    override suspend fun validUser(userLogin: UserLogin) {
        val user: User? = model.valildUserData(userLogin)
        user?.let { user ->
            interactorView.login(user)
        }?: interactorView.error()
    }
}