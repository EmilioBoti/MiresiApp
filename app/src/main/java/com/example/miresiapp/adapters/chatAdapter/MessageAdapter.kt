package com.example.miresiapp.adapters.chatAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.models.Message

class MessageAdapter(private val listMessage: MutableList<Message>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.message_item, null)
        return MessageViewHolder(view)
    }
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bindData(listMessage[position])
    }

    override fun getItemCount(): Int = listMessage.size

    class MessageViewHolder(itenView: View): RecyclerView.ViewHolder(itenView) {
        private val from: TextView = itenView.findViewById(R.id.from)
        private val sms: TextView = itenView.findViewById(R.id.messageText)

        fun bindData(message: Message) {
            from.text = message.fromUser
            sms.text = message.sms
        }
    }
}