package com.example.miresiapp.businessLogic.forum

import com.example.miresiapp.models.ForumModel

interface IForum {
    interface ViewPresenter{
        fun showForums(list: MutableList<ForumModel>)
        fun setChips(list: MutableList<CategoryModel>)
    }
    interface Presenter{
        suspend fun requestForums()
        suspend fun requestCategories()
        suspend fun filterForums(name: String)
    }
    interface ModelPresenter{
        suspend fun getForums(): MutableList<ForumModel>?
        suspend fun getFilterForums(name: String): MutableList<ForumModel>?
        suspend fun getCategories(): MutableList<CategoryModel>?
    }
}