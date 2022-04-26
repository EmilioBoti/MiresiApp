package com.example.miresiapp.businessLogic.residence.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.residence.adapters.viewHolders.CommentViewHolder
import com.example.miresiapp.businessLogic.residence.adapters.viewHolders.RoomViewHolder
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.models.Room

class CommentAdapter(private val listRoom: MutableList<CommentModel>, private val listener: OnClickItemView): RecyclerView.Adapter<CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, null)
        return CommentViewHolder(view, listener)
    }
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindData(listRoom[position])
    }

    override fun getItemCount(): Int = listRoom.size
}