package com.example.miresiapp.businessLogic.residence.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.residence.adapters.viewHolders.RoomViewHolder
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Room

class RoomAdapter(private val listRoom: MutableList<Room>, private val listener: OnClickItemView): RecyclerView.Adapter<RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.room_item, null)
        return RoomViewHolder(view, listener)
    }
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bindData(listRoom[position])
    }

    override fun getItemCount(): Int = listRoom.size
}