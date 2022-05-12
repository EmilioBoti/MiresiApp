package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.miresiapp.R
import com.example.miresiapp.SocketCon
import com.example.miresiapp.models.Message
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.Notifications
import com.example.miresiapp.utils.toast
import com.example.miresiapp.views.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.gson.Gson
import io.socket.client.Socket

class DashBoardActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var residencesFragment: ResidenceFragment
    private var cityId: String? = null
    private lateinit var mSocket: Socket
    private var userId: Int? = 0
    private lateinit var message: Message
    private var gson: Gson = Gson()
    private val POST_TAG: String = "POST"
    private val FORUM_TAG: String = "FORUM"
    private val RESI_TAG: String = "RESI"
    private val FAVORITE_TAG: String = "FAVORITE"
    private val PROFILE_TAG: String = "PROFILE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
    }

    override fun onStart() {
        super.onStart()
        SocketCon.setSocket()
        mSocket = SocketCon.getSocket()
        mSocket.connect()

        userId = LocalData.getCurrentUserId(applicationContext)
        Notifications.createNotificationChannel(applicationContext)
        socketEventListenner()
        checkFrag(intent)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener(this)

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val current = supportFragmentManager.findFragmentById(R.id.viewContainer)

        if (current != HomeFragment()){
            setFragmentView(HomeFragment())
            for (i in 0..supportFragmentManager.backStackEntryCount){
                supportFragmentManager.popBackStack()
            }
        } else {
            onBackPressed()
        }
    }
    private fun socketEventListenner(){
        mSocket.on("private", ) { data ->
            val d = data[0]
            message = gson.fromJson<Message>(d.toString(), Message::class.java)
            if (userId != message.userSenderId) Notifications.showNotifycationMusic(applicationContext, message)
        }
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
    private fun setBackFragmentView(fragment: Fragment, tag: String) {
        val current = supportFragmentManager.findFragmentById(R.id.viewContainer)

        current?.tag?.let {
            if (current.tag != tag){
                navTo(fragment, tag)
            }
        }?: navTo(fragment, tag)

    }
    private fun navTo(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.viewContainer, fragment, tag)
            .addToBackStack(tag)
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
            setBackFragmentView(residencesFragment, RESI_TAG)
        }?: if (frag.size > 0) {
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
                setBackFragmentView(RoomFragment(), POST_TAG)
               return true
            }
            R.id.pageForum -> {
                setBackFragmentView(ForumFragment(), FORUM_TAG)
                return true
            }
            R.id.pageFavorite ->{
                setBackFragmentView(FavoriteFragment(), FAVORITE_TAG)
                return true
            }
            R.id.pageProfile ->{
                setBackFragmentView(ProfileFragment(), PROFILE_TAG)
                return true
            }
            else -> { false }
        }
        return true
    }
}