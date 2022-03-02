package com.example.miresiapp.models

import android.util.Log
import com.example.miresiapp.businessLogic.city.ICityInter
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

class DataProvider: ICityInter.ModelPresenter {
    private var listCities: MutableList<City>
    private lateinit var okHttpClient: OkHttpClient

    init {
        listCities = mutableListOf()
    }

    override suspend fun reqListCities(): String{
        var arr = "[]"
        okHttpClient = OkHttpClient()
        try {
            val request = Request.Builder().url(Consts.URLCITIES)
                .build()

            val list = withContext(Dispatchers.IO){
                okHttpClient.newCall(request).execute()
            }
            arr = list.body!!.string()
        }catch (err: Exception){

        }
        return arr
    }
}