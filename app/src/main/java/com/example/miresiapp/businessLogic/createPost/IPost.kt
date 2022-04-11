package com.example.miresiapp.businessLogic.createPost

import com.example.miresiapp.models.Residence

interface IPost {
    interface ViewPresenter{
        fun setDropDown(list: MutableList<Residence>?)
    }
    interface Presenter{
        suspend fun requestResi(city: String)
    }
    interface ModelPresenter{
        suspend fun getResidences(city: String): MutableList<Residence>?
    }
}