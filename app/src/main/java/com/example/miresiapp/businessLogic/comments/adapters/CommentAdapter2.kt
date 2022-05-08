package com.example.miresiapp.businessLogic.comments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.CommentModel

class CommentAdapter2(private val listComment: MutableList<CommentModel>, private val listener: OnClickItemView): RecyclerView.Adapter<CommentViewHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder2 {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.comment_item2, null)
        return CommentViewHolder2(view, listener)
    }
    override fun onBindViewHolder(holder: CommentViewHolder2, position: Int) {
        holder.bindData(listComment[position])
    }
    override fun getItemCount(): Int = listComment.size
}