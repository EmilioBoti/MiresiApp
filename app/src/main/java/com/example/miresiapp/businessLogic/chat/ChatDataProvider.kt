package com.example.miresiapp.businessLogic.chat

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.User
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.miresiapp.businessLogic.chat.IChat.ChatModelPresenter
import com.example.miresiapp.models.Message

class ChatDataProvider: ChatModelPresenter {

    override suspend fun getChats(userId: Int): MutableList<User>? {
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

    override suspend fun getUser(userId: Int): MutableList<User>? {
        val result: MutableList<User>? = withContext(Dispatchers.IO){
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.getUser(userId).execute().body()
            }catch (err: Exception){
                null
            } catch (err: CancellationException){
                null
            }
        }
        return result
    }

    override suspend fun getMessages(from: Int, to: Int): MutableList<Message>? {
        val result: MutableList<Message>? = withContext(Dispatchers.IO){
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.getSMS(from, to).execute().body()
            }catch (err: Exception){
                null
            } catch (err: CancellationException){
                null
            }
        }
        return result
    }
}