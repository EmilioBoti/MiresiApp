package com.example.miresiapp.adapters.chatAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.User
import com.squareup.picasso.Picasso

class ChatAdapter(private val listChat: MutableList<User>, private val context: Context,private val listener: OnClickItemView): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){

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

    class ChatViewHolder(itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val userImage: ImageView = itemView.findViewById(R.id.userImage)

        fun bindData(user: User) {
            Picasso.get()
                .load(user.image)
                .placeholder(R.drawable.person_24)
                .into(userImage)
            userName.text = user.name

            itemView.setOnClickListener { view ->
                if (RecyclerView.NO_POSITION != absoluteAdapterPosition){
                    listener.onClickItem(absoluteAdapterPosition, view)
                }
            }
        }
    }
}