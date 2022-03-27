package com.example.miresiapp.models

data class MessageModel(val from: Int,
                        val to: Int,
                        val sms: String,
                        val check: Boolean)
