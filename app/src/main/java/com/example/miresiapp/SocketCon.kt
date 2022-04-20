package com.example.miresiapp

import android.util.Log
import com.example.miresiapp.utils.Consts
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketCon {
    lateinit var mSocket: Socket
    val host: String = "https://miresi-chat.herokuapp.com/"

    @Synchronized
    fun setSocket(){
        try {
            mSocket = IO.socket(Consts.HOST).connect()
        }catch (err: URISyntaxException){
            Log.e("socketErr", err.reason)
        }
    }
    @Synchronized
    fun getSocket(): Socket{
        return mSocket
    }
}