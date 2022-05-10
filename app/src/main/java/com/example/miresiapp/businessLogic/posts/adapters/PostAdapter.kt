package com.example.miresiapp.businessLogic.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.businessLogic.posts.viewHolders.PostViewHolder
import com.example.miresiapp.databinding.PostItemBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.PostModel

class PostAdapter(private val list: MutableList<PostModel>,private val userId:Int ,private val listener: OnClickItemView): RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return PostViewHolder(binding, userId ,listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}