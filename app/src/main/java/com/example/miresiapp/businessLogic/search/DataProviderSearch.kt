package com.example.miresiapp.businessLogic.search

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.City
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataProviderSearch: ISearch.ModelPresenter {

    override suspend fun makeCall(name: String): MutableList<City>? {
        return try {
            val retrofit = Retrofit.Builder()
                .baseUrl(Consts.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ApiEndPoint::class.java)

            val name = withContext(Dispatchers.IO){
                service.searchCity(name).execute()
            }
            return name.body()
        }catch (err : Exception){
          null
        }
    }

}