package com.example.miresiapp.businessLogic.login

import com.example.miresiapp.SocketCon
import com.example.miresiapp.businessLogic.login.ILoginInteractor.PresenterView
import com.example.miresiapp.businessLogic.login.ILoginInteractor.Presenter
import com.example.miresiapp.models.DataProvider
import com.example.miresiapp.models.User
import com.example.miresiapp.models.UserLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginBusinessLogic(private val interactorView: PresenterView, private val model: DataProvider): Presenter  {

    override fun validUser(userLogin: UserLogin) {
        GlobalScope.launch(Dispatchers.Main){
            val user: User? = model.valildUserData(userLogin)
            user?.let { user ->
                interactorView.login(user)
            }?: interactorView.error()
        }
    }
}