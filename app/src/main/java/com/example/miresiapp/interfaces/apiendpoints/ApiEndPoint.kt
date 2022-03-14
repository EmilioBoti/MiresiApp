package com.example.miresiapp.interfaces.apiendpoints

import com.example.miresiapp.models.City
import com.example.miresiapp.models.Residence
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiEndPoint {

    @GET("api/v1/residences/{city}")
    fun getResidences(@Path("city") city: String): Call<MutableList<Residence>>

    @GET("api/city/c/{name}")
    fun searchCity(@Path("name") name: String): Call<MutableList<City>>

}