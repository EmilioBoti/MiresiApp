package com.example.miresiapp.businessLogic.forum.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.forum.adapters.viewholders.CommViewHolder
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.forumModels.Comment
import com.example.miresiapp.models.forumModels.ForumComment

class CommentAdapter(private val list: MutableList<Comment>, private val listener: OnClickItemView): RecyclerView.Adapter<CommViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item2, parent,false)
        return CommViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CommViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}