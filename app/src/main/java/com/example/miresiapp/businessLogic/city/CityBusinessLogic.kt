package com.example.miresiapp.businessLogic.city

import com.example.miresiapp.models.City
import com.example.miresiapp.models.DataProvider
import com.google.gson.Gson
import com.example.miresiapp.businessLogic.city.ICityInter.Presenter
import com.example.miresiapp.businessLogic.city.ICityInter.ViewPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CityBusinessLogic(private val viewer: ViewPresenter,private val model: DataProvider): Presenter {
    private lateinit var listCities: MutableList<City>
    private lateinit var gson: Gson

     override fun getListCities() {
         gson = Gson()
         listCities = mutableListOf()

         GlobalScope.launch(Dispatchers.Main){
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
}