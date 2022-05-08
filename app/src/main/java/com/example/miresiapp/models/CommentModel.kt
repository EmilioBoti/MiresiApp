package com.example.miresiapp.models

data class CommentModel(
    val userId: Int,
    val userName: String?,
    val resiId: Int,
    val comments: String,
    val dateCreated: String,
    val userImage: String?
    )
