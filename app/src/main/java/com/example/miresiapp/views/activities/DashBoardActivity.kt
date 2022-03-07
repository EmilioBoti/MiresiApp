package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.miresiapp.R
import com.example.miresiapp.views.fragments.FavoriteFragment
import com.example.miresiapp.views.fragments.ForumFragment
import com.example.miresiapp.views.fragments.HomeFragment
import com.example.miresiapp.views.fragments.RoomFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class DashBoardActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
    }

    override fun onStart() {
        super.onStart()
        setFragmentView(HomeFragment())

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener(this)
    }
    private fun setFragmentView( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.pageHome->{
                setFragmentView(HomeFragment())
               return true
            }
            R.id.pageRoom -> {
                setFragmentView(RoomFragment())
               return true
            }
            R.id.pageForum->{
                setFragmentView(ForumFragment())
                return true
            }
            R.id.pageFavorite->{
                setFragmentView(FavoriteFragment())
                return true
            }
            else -> { false }
        }
        return false
    }
}