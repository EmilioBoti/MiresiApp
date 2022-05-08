package com.example.miresiapp.businessLogic.comments.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.CommentModel
import com.squareup.picasso.Picasso

class CommentViewHolder2 (itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView){
    private val userImage: ImageView? = itemView.findViewById(R.id.userImage)
    private val userName: TextView = itemView.findViewById(R.id.userName)
    private val date: TextView = itemView.findViewById(R.id.date)
    private val comment: TextView = itemView.findViewById(R.id.comment)

    fun bindData(commentModel: CommentModel) {
        commentModel.userImage?.let {
            Picasso.get().load(it).fit().into(userImage)
        }
        userName.text = commentModel.userName
        date.text = commentModel.dateCreated
        comment.text = commentModel.comments
    }
}
