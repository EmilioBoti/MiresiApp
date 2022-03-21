package com.example.miresiapp.utils

import android.content.Context
import android.widget.Toast

fun <T: Context, R> toast(con: T?, sms: R){
    Toast.makeText(con, sms.toString(), Toast.LENGTH_SHORT).show()
}

