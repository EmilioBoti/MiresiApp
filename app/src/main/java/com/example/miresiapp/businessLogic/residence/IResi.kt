package com.example.miresiapp.businessLogic.residence

import com.example.miresiapp.models.Residence

interface IResi {
    interface PresenterView{
        fun getResi(list: MutableList<Residence>?)
        fun error(err: String)
    }
    interface Presenter{
        suspend fun makeRequest(city: String)
        suspend fun getSingleResi(id: Int)
    }
    interface PresenterModel{
        suspend fun getResiFromCity(city: String): MutableList<Residence>?
        suspend fun getSingleResi(id: Int): MutableList<Residence>?
    }
}