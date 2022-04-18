package com.example.miresiapp.businessLogic.rooms

import com.example.miresiapp.businessLogic.createPost.PostDataProvider
import com.example.miresiapp.models.PostModel

class RoomsLogicImpl(private val viewer: IRooms.ViewPresenter,private val model: PostDataProvider): IRooms.Presenter {
    override suspend fun requestPost() {
        val list: MutableList<PostModel>? = model.getPost()
        viewer.showPost(list!!)
    }

}