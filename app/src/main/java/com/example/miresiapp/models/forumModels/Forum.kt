package com.example.miresiapp.models.forumModels

data class Forum(
    var name: String,
    var creatorUser: Int,
    var about: String? = "",
    var resiId: Int?= 0,
    var cityId: Int?= 0,
    var categoryId: Int? = 0,
    var image: Int? = 0
    )
