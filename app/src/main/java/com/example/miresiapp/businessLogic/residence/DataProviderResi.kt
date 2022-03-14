package com.example.miresiapp.businessLogic.residence

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.Residence
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataProviderResi: IResi.PresenterModel{
    private lateinit var residence: MutableList<Residence>

    override suspend fun getResiFromCity(city: String): MutableList<Residence>? {

        return try {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Consts.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ApiEndPoint::class.java)
            val listResi = withContext(Dispatchers.IO){
                service.getResidences(city).execute()
            }
            return listResi.body()
        }catch (err: Exception){
            null
        }
    }
}