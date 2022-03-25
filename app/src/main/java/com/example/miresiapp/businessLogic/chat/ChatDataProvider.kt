package com.example.miresiapp.businessLogic.chat

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.User
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatDataProvider: IChat.ChatModelPresenter {

    override suspend fun getChats(userId: Int): MutableList<User>? {

        //does not work!!
        val result: MutableList<User>? = withContext(Dispatchers.IO){
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.userChats(userId).execute().body()
            }catch (err: Exception){
                null
            } catch (err: CancellationException){
                null
            }
        }
        return result
    }
}