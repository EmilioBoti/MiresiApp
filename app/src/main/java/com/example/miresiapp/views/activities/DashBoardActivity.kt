package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.miresiapp.R
import com.example.miresiapp.views.fragments.CityFragment
import com.example.miresiapp.views.fragments.RoomFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashBoardActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

    }

    override fun onStart() {
        super.onStart()
        setFragmentView(CityFragment())
        //Toast.makeText(this, "ok",Toast.LENGTH_SHORT).show()

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menu ->
            when(menu.itemId) {
                R.id.pageCity->{
                    setFragmentView(CityFragment())
                    true
                }
                R.id.pageRoom -> {
                    setFragmentView(RoomFragment())
                    true
                }
                else -> { false }
            }
        }
    }
    private fun setFragmentView( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, fragment)
            .commit()
    }
}