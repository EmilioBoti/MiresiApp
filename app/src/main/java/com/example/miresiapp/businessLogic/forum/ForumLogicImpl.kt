package com.example.miresiapp.businessLogic.forum

import com.example.miresiapp.models.ForumModel

class ForumLogicImpl(private val viewer: IForum.ViewPresenter,private val model: IForum.ModelPresenter, ): IForum.Presenter {
    private var list: MutableList<ForumModel>? = null
    private var listCategory: MutableList<CategoryModel>? = null


    override suspend fun requestForums() {
        list = model.getForums()
        list?.let {
            viewer.showForums(it)
        }
    }

    override suspend fun requestCategories() {
        listCategory = model.getCategories()
        listCategory?.let {
            viewer.setChips(it)
        }
    }

    override suspend fun filterForums(name: String) {
        list = model.getFilterForums(name)
        list?.let {
            viewer.showForums(it)
        }

    }
}