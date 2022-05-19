package com.example.miresiapp.models

data class Residence(val id: Int,
                     val resiName: String,
                     val location: String,
                     val phone_number: String,
                     val description: String,
                     val email: String,
                     val link: String,
                     val gym: Int,
                     val library: Int,
                     val laundry: Int,
                     val parking_bicycle: Int,
                     val parking_car: Int,
                     val parking_motorcycle: Int,
                     val id_city: Int,
                     val image: String,
                     var idResiFavorite: Int?,
                     val idUser: Int?,
                     var favouriteIdU: Int?,
                     val city: String?,
                     val priceFrom: Int?
                     )
