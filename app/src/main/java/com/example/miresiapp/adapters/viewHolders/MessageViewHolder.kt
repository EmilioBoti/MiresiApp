package com.example.miresiapp.adapters.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.models.Message

class MessageViewHolder(itenView: View): RecyclerView.ViewHolder(itenView) {
    //private val from: TextView = itenView.findViewById(R.id.from)
    private val sms: TextView = itenView.findViewById(R.id.messageText)

    fun bindData(message: Message) {
        sms.text = message.sms
    }
}
