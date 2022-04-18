package com.example.miresiapp.views.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.SocketCon
import com.example.miresiapp.businessLogic.createPost.PostDataProvider
import com.example.miresiapp.businessLogic.rooms.IRooms
import com.example.miresiapp.businessLogic.rooms.RoomsLogicImpl
import com.example.miresiapp.businessLogic.rooms.adapters.PostAdapter
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.PostModel
import com.example.miresiapp.utils.toast
import com.example.miresiapp.views.activities.ChatActivity
import com.example.miresiapp.views.activities.CreatePost
import com.example.miresiapp.views.activities.MessengerActivity
import com.example.miresiapp.views.activities.ResiInfoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.socket.client.Socket
import kotlinx.coroutines.launch

class RoomFragment : Fragment(), View.OnClickListener, IRooms.ViewPresenter, OnClickItemView {
    private lateinit var chatIcon: ImageView
    private lateinit var createPost: FloatingActionButton
    private var idUser: Int? = null
    private var fragmentLayout: FrameLayout? = null
    private var listPost: MutableList<PostModel> = mutableListOf()
    private lateinit var postContainer: RecyclerView
    private lateinit var roomsLogicImpl: RoomsLogicImpl
    private lateinit var postDataProvider: PostDataProvider
    private lateinit var postAdapter: PostAdapter
    private lateinit var mSocket: Socket

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatIcon = view.findViewById(R.id.chat)
        postContainer = view.findViewById(R.id.postContainer)

        fragmentLayout = activity?.findViewById<FrameLayout>(R.id.loginFrag)
        createPost = view.findViewById(R.id.createPoast)
        createPost.setOnClickListener(this)

        postDataProvider = PostDataProvider()
        roomsLogicImpl = RoomsLogicImpl(this, postDataProvider)

    }

    override fun onStart() {
        super.onStart()
        idUser = getCurrentUserData()

        SocketCon.setSocket()
        mSocket = SocketCon.getSocket()

        lifecycleScope.launch {
            roomsLogicImpl.requestPost()
        }

        chatIcon.setOnClickListener {
            idUser = getCurrentUserData()
            this.idUser?.let {
                if(it != 0){
                    conn()
                    navigateTo(it)
                }
                else toLogin()
            }?: toLogin()
        }
    }

    private fun getUserLoged() {
        mSocket.on("connect") {
            conn()
        }
    }

    private fun conn() {
        idUser = getCurrentUserData()
        idUser?.let {
            mSocket.emit("user", it, mSocket.id())
        }
    }

    private fun toLogin(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            ?.addToBackStack(null)
            ?.replace(R.id.loginFrag, LoginFragment())
            ?.commit()
    }
    private fun getCurrentUserData(): Int? {
        val prefe = activity?.getSharedPreferences(resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
        return prefe?.getInt("userId", 0)
    }

    private fun navigateTo(idUser: Int) {
        Intent(activity, ChatActivity::class.java).apply {
            this.putExtra("id", idUser)
            startActivity(this)
        }
    }

    override fun onClick(v: View?) {
        //idUser = getCurrentUserData()
        idUser?.let {
            if(it != 0) Intent(activity, CreatePost::class.java).apply {
                startActivity(this)
            }
            else toLogin()
        }?: toLogin()

    }

    override fun showPost(list: MutableList<PostModel>) {
        listPost = list
        postAdapter = PostAdapter(listPost, this)
        postContainer.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.VERTICAL, false)
            adapter = postAdapter
        }
    }

    override fun onClickItem(pos: Int, view: View) {
        when(view.id) {
            R.id.chatTo -> {
                navToMessanger(pos)
            }
            R.id.seeTo -> {
                navToRoom(pos)
            }
        }

    }

    override fun addFavoriteItem(pos: Int, view: View) {
        TODO("Not yet implemented")
    }

    private fun navToMessanger(pos: Int) {
        conn()
        idUser?.let {
            if (it != 0) {
                Intent(activity?.applicationContext, MessengerActivity::class.java).apply {
                    putExtra("name", listPost[pos].userName)
                    putExtra("from", idUser)
                    putExtra("to", listPost[pos].userId)
                    startActivity(this)
                }
            }else toLogin()
        }?: toLogin()
    }
    private fun navToRoom(pos: Int) {
        //it not suppost to go to this screen, just for testing.
        Intent(activity?.applicationContext, ResiInfoActivity::class.java).apply {
            putExtra("id", listPost[pos].resiId)
            startActivity(this)
        }
    }
}