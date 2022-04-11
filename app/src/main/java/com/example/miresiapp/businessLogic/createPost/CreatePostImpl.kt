package com.example.miresiapp.businessLogic.createPost

import com.example.miresiapp.businessLogic.createPost.IPost.ViewPresenter
import com.example.miresiapp.models.Residence

class CreatePostImpl(private val viewer: ViewPresenter, private val model: PostDataProvider): IPost.Presenter {
    private var list: MutableList<Residence>? = null

    override suspend fun requestResi(city: String) {
        list = model.getResiFromCity(city)
        viewer.setDropDown(list)
    }

}