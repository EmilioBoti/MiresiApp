package com.example.miresiapp.models

data class Room(
    val id: Int,
    val name: String,
    val id_type: Int,
    val price: Int,
    val ac: Int,
    val heating: Int,
    val fridge: Int,
    val id_resi: Int,
    val type_name: String,
    val kitchen: Int,
    val shared_kitchen: Int,
    val shared_room: Int,
    val shared_bathroom: Int,
    val image: String
)