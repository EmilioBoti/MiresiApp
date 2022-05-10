package com.example.miresiapp.businessLogic.residence.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.models.Room
import com.squareup.picasso.Picasso

class CommentViewHolder(itemView: View, private val listener: OnClickItemView) : RecyclerView.ViewHolder(itemView) {
    private val username: TextView = itemView.findViewById(R.id.userName)
    private val date: TextView = itemView.findViewById(R.id.date)
    private val comment: TextView = itemView.findViewById(R.id.comment)

    fun bindData(commentModel: CommentModel){
        username.text = commentModel.userName
        date.text = commentModel.dateCreated
        comment.text = commentModel.comments
    }

}