package com.example.miresiapp.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.miresiapp.R
import com.example.miresiapp.views.activities.ChatActivity
import com.example.miresiapp.views.activities.MainActivity

class RoomFragment : Fragment() {
    private lateinit var chatIcon: ImageView
    private var idUser: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatIcon = view.findViewById(R.id.chat)

    }

    override fun onStart() {
        super.onStart()

        chatIcon.setOnClickListener {
            this.idUser?.let {
                navigateTo(it)
            }?: Intent(activity, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(this)
            }
        }
    }

    private fun navigateTo(idUser: Int){
        Intent(activity, ChatActivity::class.java).apply {
            this.putExtra("id", idUser)
            startActivity(this)
        }
    }
}