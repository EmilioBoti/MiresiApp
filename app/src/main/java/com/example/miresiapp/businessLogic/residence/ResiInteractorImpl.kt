package com.example.miresiapp.businessLogic.residence

import com.example.miresiapp.businessLogic.residence.IResi.Presenter
import com.example.miresiapp.businessLogic.residence.IResi.PresenterModel
import com.example.miresiapp.businessLogic.residence.IResi.PresenterView
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ResiInteractorImpl(private val view: PresenterView, private val model: PresenterModel): Presenter {
    private var list: MutableList<Residence>? = null
    private var listRoom: MutableList<Room>? = null


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
}