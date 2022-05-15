package com.example.miresiapp.utils

class Consts {
    companion object {
        const val HOST = "http://192.168.1.147:3000/"
        //const val HOST: String = "https://miresi-chat.herokuapp.com/"
        const val BASEURL = "${HOST}api/v1/"
        const val URLCITIES = "${BASEURL}cities"
        const val URLLogin = "${BASEURL}login"
    }
}
