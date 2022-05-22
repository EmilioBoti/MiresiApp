package com.example.miresiapp.models.forumModels

data class ForumComment(
    val forumId: Int,
    val userId: Int,
    val comments: String,
    val fatherId: Int?
)
