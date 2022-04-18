package com.example.miresiapp.businessLogic.createPost

import com.example.miresiapp.models.*

interface IPost {
    interface ViewPresenter{
        fun setDropDown(list: MutableList<Residence>?)
        fun setCitiesValues(list: MutableList<City>?)
        fun setRoomsValues(list: MutableList<Room>?)
        fun postCreated(valid: Boolean)
        fun errorValidDate(err: String)
    }
    interface Presenter{
        suspend fun requestResi(city: String)
        suspend fun requestCities()
        suspend fun requestRooms(id: Int)
        suspend fun createPost(resiId: Int, roomId: Int, dateStart: String, dateEnd: String)

    }
    interface ModelPresenter{
        suspend fun getAllCities(): MutableList<City>?
        suspend fun getRooms(id: Int): MutableList<Room>?
        suspend fun insertPost(post: Post): MutableList<Post>?
        suspend fun getPost(): MutableList<PostModel>?
    }
}