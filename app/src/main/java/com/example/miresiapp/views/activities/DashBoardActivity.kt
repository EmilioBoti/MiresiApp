package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.miresiapp.R
import com.example.miresiapp.utils.toast
import com.example.miresiapp.views.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class DashBoardActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var residencesFragment: ResidencesFragment
    private var cityId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
    }

    override fun onStart() {
        super.onStart()

        checkFrag(intent)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener(this)
    }

    private fun setFragmentView( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, fragment)
            //.addToBackStack(null)
            .commit()
    }private fun setBackFragmentView( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun checkFrag(intent: Intent?){
        cityId = intent?.extras?.getString("city")
        residencesFragment = ResidencesFragment()

        cityId?.let {
            val data = Bundle().apply {
                putString("city", it)
            }
            residencesFragment = ResidencesFragment().apply {
                arguments = data
            }
            setBackFragmentView(residencesFragment)
        }?: setFragmentView(HomeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.pageHome->{
                setFragmentView(HomeFragment())
               return true
            }
            R.id.pageRoom -> {
                setBackFragmentView(RoomFragment())
               return true
            }
            R.id.pageForum->{
                setBackFragmentView(ForumFragment())
                return true
            }
            R.id.pageFavorite->{
                setBackFragmentView(FavoriteFragment())
                return true
            }
            else -> { false }
        }
        return false
    }
}