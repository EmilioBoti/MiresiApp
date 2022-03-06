package com.example.miresiapp.businessLogic.city

import com.example.miresiapp.models.City

interface ICityInter {
    interface ViewPresenter{
        fun setListCities(listCities: MutableList<City>)
    }
    interface Presenter{
        fun getListCities()
    }
    interface ModelPresenter{
        suspend fun reqListCities(): String
    }
}