package com.example.miresiapp.utils

import android.content.Context
import android.os.Bundle
import com.example.miresiapp.R
import com.example.miresiapp.models.User

class LocalData {
    companion object {
        private const val id: String = "userId"
        private const val name: String = "name"
        private const val email: String = "email"
        private const val image: String = "image"
        private const val country: String = "country"
        private const val gender: String = "gender"
        private const val age: String = "age"

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
        fun saveUserCountry(context: Context, key: String, country: String): Boolean {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
                ?.edit()
            prefe?.apply {
                putString(key, country)
                apply()
            }
            return true
        }
        fun saveUserAge(context: Context, key: String, age: String): Boolean {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
                ?.edit()
            prefe?.apply {
                putString(key, age)
                apply()
            }
            return true
        }
        fun getCurrentUserCountry(context: Context): String? {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return prefe?.getString(country, "None")
        }
        fun getCurrentUserAge(context: Context, key: String): String? {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return prefe?.getString(key, "None")
        }
        fun getCurrentUserName(context: Context): String? {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return prefe?.getString(name, "None")
        }
        fun getImageUser(context: Context): String?{
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return  prefe?.getString(image, null)
        }
        fun getEmailUser(context: Context): String?{
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return  prefe?.getString(email, null)
        }
        fun getCurrentUserId(context: Context): Int? {
            val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            return prefe?.getInt(id, 0)
        }
    }
}