package com.example.miresiapp.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.miresiapp.R
import com.example.miresiapp.SocketCon
import com.example.miresiapp.views.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import io.socket.client.Socket

class DashBoardActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var residencesFragment: ResidenceFragment
    private var cityId: String? = null
    private lateinit var mSocket: Socket
    private var userId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
    }

    override fun onStart() {
        super.onStart()

        userId = getCurrentUserData()

        SocketCon.setSocket()
        mSocket = SocketCon.getSocket()
        mSocket.connect()
        checkFrag(intent)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener(this)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        checkFrag(intent)

    }
    private fun getCurrentUserData(): Int? {
        val prefe = getSharedPreferences(resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
        return prefe?.getInt("userId", 0)
    }
    private fun conn() {
        mSocket.on("connect"){
            userId?.let {
                if (it !=  0) mSocket.emit("user", it, mSocket.id())
            }
        }
    }
    private fun setFragmentView( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, fragment)
            //.addToBackStack(null)
            .commit()
    }
    private fun setBackFragmentView( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun checkFrag(intent: Intent?) {
        cityId = intent?.extras?.getString("city")
        residencesFragment = ResidenceFragment()
        val frag = supportFragmentManager.fragments
        conn()
        cityId?.let {
            val data = Bundle().apply { putString("city", it) }
            residencesFragment = ResidenceFragment().apply {
                arguments = data
            }
            setFragmentView(residencesFragment)
        }?: if (frag.size > 0){
            setFragmentView(frag[frag.size-1])
        }else setFragmentView(HomeFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.pageHome -> {
                setFragmentView(HomeFragment())
               return true
            }
            R.id.pageRoom -> {
                setBackFragmentView(RoomFragment())
               return true
            }
            R.id.pageForum -> {
                setBackFragmentView(ForumFragment())
                return true
            }
            R.id.pageFavorite ->{
                setBackFragmentView(FavoriteFragment())
                return true
            }
            else -> { false }
        }
        return true
    }
}