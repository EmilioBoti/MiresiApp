package com.example.miresiapp.businessLogic.createPost

import com.example.miresiapp.businessLogic.residence.DataProviderResi
import com.example.miresiapp.models.Residence

class PostDataProvider: IPost.ModelPresenter, DataProviderResi() {
    var listResi: MutableList<Residence>? = null

    override suspend fun getResidences(city: String): MutableList<Residence>? {



        return null
    }
}