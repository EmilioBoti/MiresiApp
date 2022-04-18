package com.example.miresiapp.models

data class PostModel(
    val postId: Int,
    val roomId: Int,
    val roomName: String,
    val price: String,
    val roomImg: String,
    val resiId: Int,
    val resiName: String,
    val dateStart: String,
    val dateEnd: String,
    val userId: Int,
    val userName: String,
    val userImg: String
)