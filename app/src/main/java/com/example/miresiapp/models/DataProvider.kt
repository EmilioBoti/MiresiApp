package com.example.miresiapp.models

import android.util.Log
import com.example.miresiapp.SocketCon
import com.example.miresiapp.utils.Consts
import okhttp3.OkHttpClient
import okhttp3.Request
import com.example.miresiapp.businessLogic.city.ICityInter.ModelPresenter
import com.example.miresiapp.businessLogic.login.ILoginInteractor.ModelLogin
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.Exception


class DataProvider: ModelPresenter, ModelLogin {
    private val mediaType = "application/json".toMediaTypeOrNull()
    private var listCities: MutableList<City> = mutableListOf()
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var gson: Gson

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
        }catch (err: CancellationException){
            Log.e("err", err.toString())
        }
        return arr
    }

    override suspend fun valildUserData(user: UserLogin): User? {
        okHttpClient = OkHttpClient()
        gson = Gson()
        val body = gson.toJson(user)
        var userLoged: User? = null

        try {
            val request = Request.Builder()
                .url(Consts.URLLogin)
                .post(body.toRequestBody(this.mediaType))
                .build()

            val list = withContext(Dispatchers.IO){
                okHttpClient.newCall(request).execute()
            }
            userLoged = gson.fromJson(list.body!!.string(), User::class.java)
            SocketCon.setSocket()
            updateUserSocket(userLoged.id)
        }catch (err: Exception){
            Log.e("err", err.toString())
        }
        return userLoged
    }

    override suspend fun updateUserSocket(id: Int) {
        okHttpClient = OkHttpClient()
        gson = Gson()
        val mSocket = SocketCon.getSocket()
        mSocket.connect()

        mSocket.on("connect"){
            try {
                val request = Request.Builder()
                    .url("${Consts.BASEURL}user/${id}/${mSocket.id()}")
                    .build()

                GlobalScope.launch(Dispatchers.IO){
                    okHttpClient.newCall(request).execute()
                }
            }catch (err: Exception){
                Log.e("err", err.toString())
            }
        }

    }
}