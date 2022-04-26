package com.example.miresiapp.businessLogic.residence

import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room

interface IResi {
    interface PresenterView{
        fun getResi(list: MutableList<Residence>?)
        fun setRooms(list: MutableList<Room>)
        fun setComments(list: MutableList<CommentModel>)
        fun error(err: String)
    }
    interface Presenter{
        suspend fun makeRequest(city: String)
        suspend fun getSingleResi(id: Int)
        suspend fun getRooms(id: Int)
        suspend fun getComments(id: Int, limit: Int)
    }
    interface PresenterModel{
        suspend fun getResiFromCity(city: String): MutableList<Residence>?
        suspend fun getSingleResi(id: Int): MutableList<Residence>?
        suspend fun getRooms(id: Int): MutableList<Room>?
        suspend fun getComments(id: Int, limit: Int): MutableList<CommentModel>?

    }
}