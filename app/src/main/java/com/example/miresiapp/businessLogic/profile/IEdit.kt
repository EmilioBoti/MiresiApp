package com.example.miresiapp.businessLogic.profile

interface IEdit {
    interface ViewPresenter{
        fun updateUI(map: HashMap<String, Any>)
    }
    interface Presenter{
        suspend fun requestUpdate(id: Int, map: HashMap<String, Any>)
    }
    interface ModelPresenter{
        suspend fun updateUser(id: Int, map: HashMap<String, Any>): Boolean?
    }
}