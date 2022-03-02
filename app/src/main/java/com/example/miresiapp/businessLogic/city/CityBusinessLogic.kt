package com.example.miresiapp.businessLogic.city

import com.example.miresiapp.models.City
import com.example.miresiapp.models.DataProvider
import com.google.gson.Gson

class CityBusinessLogic(private val viewer: ICityInter.ViewPresenter,private val model: DataProvider): ICityInter.Presenter {
    private lateinit var listCities: MutableList<City>
    private lateinit var gson: Gson

     override suspend fun getListCities() {
         gson = Gson()
         listCities = mutableListOf()
         val obj = model.reqListCities()

         val list = gson.fromJson(obj, MutableList::class.java)

         list.forEach { city ->
             val c = gson.toJson(city)
             val objCity: City = gson.fromJson(c, City::class.java)
             listCities.add(objCity)
         }
         viewer.setListCities(listCities)
    }
}