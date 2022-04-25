package com.example.miresiapp.businessLogic.posts

import com.example.miresiapp.models.PostModel

interface IPost {
    interface ViewPresenter{
        fun showPost(list: MutableList<PostModel>)
    }
    interface Presenter{
        suspend fun requestPost()
        fun navigaateTo()
        fun navCreatePost()
        fun navToMessanger(idTo: Int, userName: String)
    }
}