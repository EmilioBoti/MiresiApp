package com.example.miresiapp.interfaces.apiendpoints

import com.example.miresiapp.models.City
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiEndPoint {

    @GET("api/v1/residences/{city}")
    fun getResidences(@Path("city") city: String): Call<MutableList<Residence>>

    @GET("api/v1/resi/{id}")
    fun getSingleResi(@Path("id") id: Int): Call<MutableList<Residence>>

    @GET("api/city/c/{name}")
    fun searchCity(@Path("name") name: String): Call<MutableList<City>>

    @GET("api/v1/chats/{idUser}")
    fun userChats(@Path("idUser") idUser: Int): Call<MutableList<User>>

}