package com.example.miresiapp.views.activities.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miresiapp.R
import com.example.miresiapp.views.fragments.ProfileFragment
import com.example.miresiapp.views.fragments.SettingFragment

class BaseSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_settings)


        setSettingFrag()
    }

    private fun setSettingFrag(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.settingViewContainer, SettingFragment())
            .commit()
    }
}