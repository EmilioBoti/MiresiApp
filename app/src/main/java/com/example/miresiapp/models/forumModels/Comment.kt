package com.example.miresiapp.models.forumModels

data class Comment(
    val id: Int,
    val forumId: Int,
    val comments: String,
    val date_created: String,
    val fatherId: Int?,
    val name: String,
    val image: String
)