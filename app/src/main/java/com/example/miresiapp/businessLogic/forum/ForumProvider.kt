package com.example.miresiapp.businessLogic.forum

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.ForumModel
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForumProvider: IForum.ModelPresenter{

    override suspend fun getForums(): MutableList<ForumModel>? {

        return withContext(Dispatchers.IO) {
            try {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Consts.HOST)
                    .build()

                val services = retrofit.create(ApiEndPoint::class.java)
                 services.getForums()?.execute()?.body()
            }catch (err: Exception){
                null
            }
        }
    }

    override suspend fun getFilterForums(name: String): MutableList<ForumModel>? {
        return withContext(Dispatchers.IO) {
            try {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Consts.HOST)
                    .build()

                val services = retrofit.create(ApiEndPoint::class.java)
                services.getFilterForums(name)?.execute()?.body()
            }catch (err: Exception){
                null
            }
        }
    }

    override suspend fun getCategories(): MutableList<CategoryModel>? {
        return withContext(Dispatchers.IO) {
            try {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Consts.HOST)
                    .build()

                val services = retrofit.create(ApiEndPoint::class.java)
                services.getAllCategories().execute().body()
            }catch (err: Exception){
                null
            }
        }
    }
}