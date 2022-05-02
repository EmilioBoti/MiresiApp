package com.example.miresiapp.businessLogic.forum

import com.example.miresiapp.models.ForumModel

interface IForum {
    interface ViewPresenter{
        fun showForums(list: MutableList<ForumModel>)
    }
    interface Presenter{
        suspend fun requestForums()
    }
    interface ModelPresenter{
        suspend fun getForums(): MutableList<ForumModel>?
    }
}