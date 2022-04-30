package com.example.miresiapp.adapters.chatAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.viewHolders.MessageViewHolder
import com.example.miresiapp.models.Message

class MessageAdapter(private val listMessage: MutableList<Message>, private val from: Int): RecyclerView.Adapter<MessageViewHolder>() {
    private val TYPE_ONE: Int = 1
    private val TYPE_TWO: Int = 2
    lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == TYPE_ONE) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, null)
            MessageViewHolder(view)
        }else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.message_item2, null)
            MessageViewHolder(view)
        }
    }
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bindData(listMessage[position])
    }

    override fun getItemCount(): Int = listMessage.size

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        return if(listMessage[position].userSenderId == from ) {
            TYPE_ONE
        }else{
            TYPE_TWO
        }
    }
}