package com.example.miresiapp.models

data class Message(
    val userSenderId: Int,
    val userReceiverId: Int,
    val fromUser: String,
    val sms: String,
    val time: String,
    val checked: Int)
