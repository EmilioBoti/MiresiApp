package com.example.miresiapp.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.User
import com.squareup.picasso.Picasso

class ChatViewHolder(itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView) {
    private val userName: TextView = itemView.findViewById(R.id.userName)
    private val userImage: ImageView = itemView.findViewById(R.id.userImage)

    fun bindData(user: User) {
        Picasso.get().load(user.image).placeholder(R.drawable.person_24)
            .into(userImage)
        userName.text = user.name.run {
            replaceFirst(this[0].toString(), this[0].titlecase())
        }
        itemView.setOnClickListener { view ->
            if (RecyclerView.NO_POSITION != absoluteAdapterPosition){
                listener.onClickItem(absoluteAdapterPosition, view)
            }
        }
    }
}