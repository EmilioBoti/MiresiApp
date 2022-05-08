package com.example.miresiapp.businessLogic.residence.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.residence.adapters.viewHolders.CommentViewHolder
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.CommentModel

class CommentAdapter(private val listComment: MutableList<CommentModel>, private val listener: OnClickItemView): RecyclerView.Adapter<CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, null)
        return CommentViewHolder(view, listener)
    }
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindData(listComment[position])
    }
    override fun getItemCount(): Int = listComment.size
}