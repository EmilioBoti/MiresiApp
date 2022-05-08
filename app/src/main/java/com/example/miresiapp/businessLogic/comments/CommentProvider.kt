package com.example.miresiapp.businessLogic.comments

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentProvider: IComment.ModelPresenter {

    override suspend fun getComments(resiId: Int, limit: Int): MutableList<CommentModel>? {

        return withContext(Dispatchers.IO){
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.getComments(resiId, limit).execute().body()
            } catch (err: Exception){
                null
            }
        }
    }
}