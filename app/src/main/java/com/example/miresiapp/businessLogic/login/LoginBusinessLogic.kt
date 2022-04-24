package com.example.miresiapp.businessLogic.login

import android.content.Context
import com.example.miresiapp.businessLogic.login.ILoginInteractor.PresenterView
import com.example.miresiapp.businessLogic.login.ILoginInteractor.Presenter
import com.example.miresiapp.models.DataProvider
import com.example.miresiapp.models.User
import com.example.miresiapp.models.UserLogin
import com.example.miresiapp.utils.LocalData

class LoginBusinessLogic(private val interactorView: PresenterView, private val model: DataProvider,private val context: Context): Presenter  {

    override suspend fun validUser(userLogin: UserLogin) {
        val user: User? = model.valildUserData(userLogin)
        user?.let { user ->
            val saved = LocalData.saveUserLogin(context, user)
            if (saved) interactorView.login(user) else interactorView.error()
        }?: interactorView.error()
    }
}