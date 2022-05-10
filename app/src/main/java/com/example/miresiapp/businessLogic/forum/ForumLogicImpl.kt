package com.example.miresiapp.businessLogic.forum

import com.example.miresiapp.models.ForumModel

class ForumLogicImpl(private val viewer: IForum.ViewPresenter,private val model: IForum.ModelPresenter, ): IForum.Presenter {
    private var list: MutableList<ForumModel>? = null

    override suspend fun requestForums() {
        list = model.getForums()
        list?.let {
            viewer.showForums(it)
        }
    }
}