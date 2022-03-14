package com.example.miresiapp.businessLogic.search
import com.example.miresiapp.businessLogic.search.ISearch.ViewPresenter
import com.example.miresiapp.businessLogic.search.ISearch.Presenter
import com.example.miresiapp.models.City

class SearchLogicImpl(private val viewPresenter: ViewPresenter,private val model: DataProviderSearch): Presenter {

    override suspend fun requestCity(name: String) {
        val cityName: MutableList<City>? = model.makeCall(name)

        cityName?.let {
            if (it.size != 0) viewPresenter.citySearched(it)
            else viewPresenter.error("Not Fount")
        }?: viewPresenter.error("Not Fount")
    }

    override suspend fun requestSuggest(name: String) {
        val cities: MutableList<City>? = model.makeCall(name)

        cities?.let {
            if (it.size != 0) viewPresenter.suggestions(it)
        }
    }
}