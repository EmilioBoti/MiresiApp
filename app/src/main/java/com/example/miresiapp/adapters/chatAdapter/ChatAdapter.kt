package com.example.miresiapp.adapters.chatAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.viewHolders.ChatViewHolder
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.User
import com.squareup.picasso.Picasso

class ChatAdapter(private val listChat: MutableList<User>, private val context: Context,private val listener: OnClickItemView): RecyclerView.Adapter<ChatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chat_item, null)
        return ChatViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bindData(listChat[position])
    }

    override fun getItemCount(): Int {
        return listChat.size
    }
}