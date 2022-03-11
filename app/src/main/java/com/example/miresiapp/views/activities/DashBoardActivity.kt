package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.miresiapp.R
import com.example.miresiapp.views.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class DashBoardActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView
    private var cityId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        //cityId = intent.extras?.getInt("id")
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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        cityId.let {
            setFragmentView(ResidencesFragment())
        }
        //Toast.makeText(applicationContext, "old activity: ${intent?.getd}", Toast.LENGTH_SHORT).show()
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