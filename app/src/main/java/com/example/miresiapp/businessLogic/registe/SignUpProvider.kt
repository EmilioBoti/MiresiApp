package com.example.miresiapp.businessLogic.registe

import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.RegisterUser
import com.example.miresiapp.models.User
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpProvider : ISignUp.ModelPresenter {

    override suspend fun resgister(newUser: RegisterUser): User? {
        return try {
            withContext(Dispatchers.IO){
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.register(newUser).execute().body()
            }
        }catch (err: Exception){
            null
        }
    }
}