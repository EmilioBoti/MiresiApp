package com.example.miresiapp.businessLogic.forum.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.forumModels.Comment
import com.squareup.picasso.Picasso

class CommViewHolder(itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView) {
    private val userName: TextView = itemView.findViewById(R.id.userName)
    private val userImg: ImageView = itemView.findViewById(R.id.userImage)
    private val comment: TextView = itemView.findViewById(R.id.comment)
    private val date: TextView = itemView.findViewById(R.id.date)

    fun bindData(comment: Comment){
        userName.text = comment.name
        Picasso.get().load(comment.image).fit().centerCrop().into(userImg)
        this.comment.text = comment.comments
        date.text = comment.date_created

        itemView.setOnClickListener {
            listener.onClickItem(absoluteAdapterPosition, it)
        }
    }
}