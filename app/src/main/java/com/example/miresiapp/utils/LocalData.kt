package com.example.miresiapp.utils

import android.content.Context
import com.example.miresiapp.R
import com.example.miresiapp.models.User

class LocalData {
    companion object {
        private const val id: String = "userId"
        private const val name: String = "name"
        private const val email: String = "email"
        private const val image: String = "image"

        fun saveUserLogin(context: Context, user: User): Boolean {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
                ?.edit()
            prefe?.apply {
                putInt(id, user.id)
                putString(name, user.name)
                putString(email, user.email)
                putString(image, user.image)
                apply()
            }
            return true
        }
        fun getImageUser(context: Context): String?{
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return  prefe?.getString("image", null)
        }
        fun getCurrentUserId(context: Context): Int? {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return prefe?.getInt(id, 0)
        }
    }
}