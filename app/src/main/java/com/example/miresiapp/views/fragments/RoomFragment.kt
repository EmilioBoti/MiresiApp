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
import com.example.miresiapp.R
import com.example.miresiapp.views.activities.ChatActivity
import com.example.miresiapp.views.activities.CreatePost
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RoomFragment : Fragment(), View.OnClickListener {
    private lateinit var chatIcon: ImageView
    private lateinit var createPost: FloatingActionButton
    private var idUser: Int? = null
    private var fragmentLayout: FrameLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatIcon = view.findViewById(R.id.chat)
        fragmentLayout = activity?.findViewById<FrameLayout>(R.id.loginFrag)
        createPost = view.findViewById(R.id.createPoast)
        createPost.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        chatIcon.setOnClickListener {
            val prefe = activity?.getSharedPreferences(resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
            idUser = prefe?.getInt("userId", 0)
            this.idUser?.let {
                if(it != 0) navigateTo(it)
                else toLogin()
            }?: toLogin()
        }
    }

    private fun toLogin(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(R.id.loginFrag, LoginFragment())
            ?.commit()
    }

    private fun navigateTo(idUser: Int){
        Intent(activity, ChatActivity::class.java).apply {
            this.putExtra("id", idUser)
            startActivity(this)
        }
    }

    override fun onClick(v: View?) {
        Intent(activity, CreatePost::class.java).apply {
            startActivity(this)
        }
    }
}