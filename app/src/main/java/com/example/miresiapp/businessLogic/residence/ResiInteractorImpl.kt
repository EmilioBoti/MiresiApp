package com.example.miresiapp.businessLogic.residence

import com.example.miresiapp.businessLogic.residence.IResi.Presenter
import com.example.miresiapp.businessLogic.residence.IResi.PresenterModel
import com.example.miresiapp.businessLogic.residence.IResi.PresenterView
import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.models.FavouriteModel
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ResiInteractorImpl(private val view: PresenterView, private val model: PresenterModel): Presenter {
    private var list: MutableList<Residence>? = null
    private var listRoom: MutableList<Room>? = null
    private var listcomments: MutableList<CommentModel>? = null

    override suspend fun makeRequest(city: String) {
        list = model.getResiFromCity(city)

        list?.let {
            view.getResi(it)
        }?: view.error("error")
    }

    override suspend fun getSingleResi(id: Int) {
        list = model.getSingleResi(id)
        list?.let {
            view.getResi(it)
        }?: view.error("error")
    }

    override suspend fun getRooms(id: Int) {
        listRoom = model.getRooms(id)
        listRoom?.let {
            view.setRooms(it)
        }
    }

    override suspend fun getComments(id: Int, limit: Int) {
        listcomments = model.getComments(id, limit)
        listcomments?.let {
            view.setComments(listcomments!!)
        }

    }

    override suspend fun addFavourite(favourite: FavouriteModel) {
         if (model.addToFavourote(favourite)){
             view.added("added")
         }
    }

    override suspend fun removeFavourite(favourite: FavouriteModel) {
        if (model.removeFromFavourote(favourite)){
            view.added("removed")
        }
    }
}