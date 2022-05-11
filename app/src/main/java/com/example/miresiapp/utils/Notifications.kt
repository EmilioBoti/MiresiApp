package com.example.miresiapp.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.miresiapp.R
import com.example.miresiapp.models.Message
import com.example.miresiapp.views.activities.ui.chat.MessengerActivity

class Notifications {
    companion object {
        private const val channelId = "com.example.miresiapp"

        fun createNotificationChannel(context: Context){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name = "com.example.musicapp"
                val descriptionText = "music notification"
                val importance = NotificationManager.IMPORTANCE_DEFAULT

                val channel = NotificationChannel(channelId, name, importance).apply {
                    description = descriptionText
                }
                //register channel in the system
                val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

        fun showNotifycationMusic(context: Context, message: Message) {
            val intent = Intent(context, MessengerActivity::class.java).apply {
                putExtra("from", message.userReceiverId)
                putExtra("to", message.userSenderId)
                putExtra("name", message.fromUser)
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notificationBuider = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.chat_24)
                //.setContentIntent(pendingIntent)
                .setContentTitle(message.fromUser)
                .setContentText(message.sms)
                .setAutoCancel(true)


            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define
                notify(message.userSenderId, notificationBuider.build())
            }
        }
    }
}