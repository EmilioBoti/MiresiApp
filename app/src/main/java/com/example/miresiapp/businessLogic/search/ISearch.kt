package com.example.miresiapp.businessLogic.search

import android.app.Activity
import com.example.miresiapp.models.City

interface ISearch {
    interface ViewPresenter{
        fun citySearched(city: MutableList<City>)
        fun suggestions(listCities: MutableList<City>)
        fun error(err: String)
    }
    interface Presenter{
        suspend fun requestCity(name: String)
        suspend fun requestSuggest(name: String)
    }
    interface ModelPresenter{
        suspend fun makeCall(name: String): MutableList<City>?
    }
}