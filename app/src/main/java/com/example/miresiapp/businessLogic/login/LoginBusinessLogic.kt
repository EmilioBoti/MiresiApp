package com.example.miresiapp.businessLogic.login

class LoginBusinessLogic(private val interactorView: ILoginInteractor.PresenterView): ILoginInteractor.Presenter  {

    override fun validUser() {
        interactorView.login()
    }
}