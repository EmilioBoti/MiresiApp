package com.example.miresiapp.businessLogic.home

import com.example.miresiapp.models.forumModels.ForumModel
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room

interface IHome {
    interface ViewPresenter {
        fun navigateTo(name: String)
        fun setResi(list: MutableList<Residence>)
        fun setRooms(list: MutableList<Room>)
        fun setForums(list: MutableList<ForumModel>)
        fun navigateToResi(id: Int)
    }
    interface Presenter{
        suspend fun requestResis(limit: Int)
        suspend fun requestRooms(limit: Int)
        suspend fun requestForums(limit: Int)
        fun naveTo(pos: Int)
    }
    interface ModelPresenter {
        suspend fun getResis(limit: Int): MutableList<Residence>?
        suspend fun getRooms(limit: Int): MutableList<Room>?
        suspend fun getForums(limit: Int): MutableList<ForumModel>?
    }
}