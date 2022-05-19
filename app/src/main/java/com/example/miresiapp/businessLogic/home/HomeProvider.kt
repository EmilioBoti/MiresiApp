package com.example.miresiapp.businessLogic.home

import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.businessLogic.residence.DataProviderResi
import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.models.User
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeProvider: IHome.ModelPresenter {

    override suspend fun getResis(limit: Int): MutableList<Residence>? {
        return withContext(Dispatchers.IO){
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.getHomeResis(limit).execute().body()
            }catch (err: Exception){
                null
            }
        }
    }

    override suspend fun getRooms(limit: Int): MutableList<Room>? {
        return withContext(Dispatchers.IO){
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.getRooms(limit).execute().body()
            }catch (err: Exception){
                null
            }
        }
    }
}