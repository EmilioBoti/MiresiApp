package com.example.miresiapp.businessLogic.forum.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.ForumModel

class ForumAdapter( private val list: MutableList<ForumModel>,private val listener: OnClickItemView): RecyclerView.Adapter<ForumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forum_item, parent,false)
        return ForumViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}