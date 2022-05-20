package com.example.miresiapp.models

data class Forum(
    val name: String,
    val creatorUser: Int,
    val about: String,
    val resiId: Int?,
    val cityId: Int?,
    val categoryId: Int?,
    val image: String?
    )
