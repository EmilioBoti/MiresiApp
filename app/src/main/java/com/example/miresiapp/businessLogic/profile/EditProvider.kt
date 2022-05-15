package com.example.miresiapp.businessLogic.profile

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditProvider: IEdit.ModelPresenter {

    override suspend fun updateUser(id: Int, map: HashMap<String, Any>): Boolean? {
        return withContext(Dispatchers.IO){
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.updateUser(id, map).execute().body()
            }catch (err: Exception){
                false
            }
        }
    }
}