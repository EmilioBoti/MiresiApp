package com.example.miresiapp.businessLogic.login

interface ILoginInteractor {
    interface PresenterView{
        fun login()
    }
    interface Presenter{
        fun validUser()
    }
}