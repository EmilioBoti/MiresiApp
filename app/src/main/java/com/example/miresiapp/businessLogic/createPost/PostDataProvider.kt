package com.example.miresiapp.businessLogic.createPost

import com.example.miresiapp.businessLogic.residence.DataProviderResi
import com.example.miresiapp.interfaces.apiendpoints.ApiEndPoint
import com.example.miresiapp.models.*
import com.example.miresiapp.utils.Consts
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class PostDataProvider: IPost.ModelPresenter, DataProviderResi() {
    var listResi: MutableList<City>? = null

    override suspend fun getAllCities(): MutableList<City>? {

        val result: MutableList<City>? = withContext(Dispatchers.IO) {
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val services = retrofit.create(ApiEndPoint::class.java)
                services.getAllCities().execute().body()
            }catch (err: Exception){
                null
            }catch (err: CancellationException){
                null
            }
        }
        return result
    }

    override suspend fun getRooms(id: Int): MutableList<Room>? {
        val result: MutableList<Room>? = withContext(Dispatchers.IO){
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.getRoomsResi(id).execute().body()
            }catch (err: Exception){
                null
            } catch (err: CancellationException){
                null
            }
        }
        return result
    }

    override suspend fun insertPost(post: Post): Boolean? {
        //it is not working properly, must be fix it
        return  withContext(Dispatchers.IO) {
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.insertPost(post).execute().body()
            }catch (err: Exception){
                null
            } catch (err: CancellationException){
                null
            }
        }
    }

    override suspend fun getPost(): MutableList<PostModel>? {
        return  withContext(Dispatchers.IO) {
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Consts.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiEndPoint::class.java)
                service.getPosts().execute().body()
            }catch (err: Exception){
                null
            } catch (err: CancellationException){
                null
            }
        }
    }
}