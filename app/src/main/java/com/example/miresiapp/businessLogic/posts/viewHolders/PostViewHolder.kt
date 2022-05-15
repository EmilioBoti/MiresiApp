package com.example.miresiapp.businessLogic.posts.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.databinding.PostItemBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.PostModel
import com.squareup.picasso.Picasso

class PostViewHolder(private val binding: PostItemBinding, private val userId: Int, private val listener: OnClickItemView): RecyclerView.ViewHolder(binding.root) {

    fun bindData(postModel: PostModel) {
        binding.roomName.text = postModel.roomName
        binding.resiName.text = postModel.resiName
        binding.dates.text = "${postModel.dateStart} - ${postModel.dateEnd}"
        Picasso.get().load(postModel.roomImg).fit().centerCrop().into(binding.imagePost)
        Picasso.get().load(postModel.userImg).fit().centerCrop().into(binding.imageUser)
        binding.userName.text = postModel.userName
        binding.priceRoom.text = "€${postModel.price}"

        if (userId == postModel.userId){
            binding.chatTo.visibility = View.GONE
        }else binding.chatTo.visibility = View.VISIBLE

        binding.chatTo.setOnClickListener { view ->
            if (RecyclerView.NO_POSITION != absoluteAdapterPosition){
                listener.onClickItem(absoluteAdapterPosition, view)
            }
        }
        binding.seeTo.setOnClickListener { view ->
            if (RecyclerView.NO_POSITION != absoluteAdapterPosition){
                listener.onClickItem(absoluteAdapterPosition, view)
            }
        }
    }
}