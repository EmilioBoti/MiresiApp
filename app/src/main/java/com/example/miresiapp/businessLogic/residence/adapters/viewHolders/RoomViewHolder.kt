package com.example.miresiapp.businessLogic.residence.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Room
import com.squareup.picasso.Picasso

class RoomViewHolder(itemView: View, private val listener: OnClickItemView) : RecyclerView.ViewHolder(itemView) {
    private val roomImage: ImageView = itemView.findViewById(R.id.roomImage)
    private val roomName: TextView = itemView.findViewById(R.id.roomName)

    fun bindData(room: Room){
        Picasso.get().load(room.image).fit().into(roomImage)
        roomName.text = room.name

        itemView.setOnClickListener { view ->
            if (RecyclerView.NO_POSITION != absoluteAdapterPosition){
                listener.onClickItem(absoluteAdapterPosition, view)
            }
        }
    }

}