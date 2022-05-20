package com.example.miresiapp.businessLogic.home

import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room

class HomePresenter(private val view: IHome.ViewPresenter, private val modelPresenter: IHome.ModelPresenter): IHome.Presenter {
    private var listResis: MutableList<Residence>? = null
    private var listRooms: MutableList<Room>? = null

    override suspend fun requestResis(limit: Int) {
        listResis = modelPresenter.getResis(limit)

        listResis?.let {
            view.setResi(it)
        }

    }

    override suspend fun requestRooms(limit: Int) {
        listRooms = modelPresenter.getRooms(limit)
        listRooms?.let {
            view.setRooms(it)
        }
    }

    override fun naveTo(pos: Int) {
        listResis?.let {
            view.navigateToResi(it[pos].id)
        }
    }

}