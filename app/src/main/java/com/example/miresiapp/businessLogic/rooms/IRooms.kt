package com.example.miresiapp.businessLogic.rooms

import com.example.miresiapp.models.PostModel

interface IRooms {
    interface ViewPresenter{
        fun showPost(list: MutableList<PostModel>)
    }
    interface Presenter{
        suspend fun requestPost()
    }
}