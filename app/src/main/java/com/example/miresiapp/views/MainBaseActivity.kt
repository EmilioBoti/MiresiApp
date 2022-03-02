package com.example.miresiapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.businessLogic.city.CityBusinessLogic
import com.example.miresiapp.businessLogic.city.ICityInter
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.models.DataProvider
import com.example.miresiapp.views.fragments.CityFragment
import com.example.miresiapp.views.fragments.RoomFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainBaseActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_base)
    }
    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, CityFragment())
            .commit()

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        //bottomNavigationView.visibility = View.GONE
        bottomNavigationView.setOnNavigationItemSelectedListener { menu->
            when(menu.itemId) {
                R.id.pageCity->{
                    setFragmentView(CityFragment())
                    true
                }
                R.id.pageRoom -> {
                    // Respond to navigation item 1 click
                    setFragmentView(RoomFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun setFragmentView( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, fragment)
            .commit()
    }
}