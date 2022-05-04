package com.example.miresiapp.models

data class User(
    val allow: Boolean,
    val id: Int,
    val name: String,
    val email: String,
    val socketId: String,
    val image: String,
    )