package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.SocketCon
import com.example.miresiapp.adapters.chatAdapter.MessageAdapter
import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.businessLogic.message.IMessenger
import com.example.miresiapp.businessLogic.message.MessengeLogicImpl
import com.example.miresiapp.databinding.ActivityMessengerBinding
import com.example.miresiapp.models.Message
import com.example.miresiapp.models.MessageModel
import com.example.miresiapp.utils.toast
import com.google.gson.Gson
import io.socket.client.Socket
import kotlinx.coroutines.launch

class MessengerActivity : AppCompatActivity(), View.OnClickListener, IMessenger.ViewPresenter {
    private lateinit var binding: ActivityMessengerBinding
    private lateinit var listMessage: MutableList<Message>
    private var data: Bundle? = null
    private var from: Int? = null
    private var to: Int? = null
    private lateinit var message: MessageModel
    private lateinit var gson: Gson
    private lateinit var model: ChatDataProvider
    private lateinit var messengeLogicImpl: MessengeLogicImpl
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessengerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent?.extras
    }

    override fun onStart() {
        super.onStart()

        gson = Gson()

        binding.btnSender.setOnClickListener(this)
        model = ChatDataProvider()
        listMessage = mutableListOf()
        messengeLogicImpl = MessengeLogicImpl(this, model)
        from = data?.getInt("from")
        to = data?.getInt("to")

        binding.userNameSelected.text = data?.getString("name", "").run {
            this!!.replaceFirst(this[0].toString(), this[0].titlecase())
        }
        
        lifecycleScope.launch {
           messengeLogicImpl.requestMessage(from!!, to!!)
        }
        binding.btnGoBack.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onClick(v: View?) {
        if(binding.boxMessage.text.isNotEmpty()){
            message = MessageModel(from!!, to!!, binding.boxMessage.text.toString().trim(), false)
            binding.boxMessage.setText("")
            messengeLogicImpl.sendMessage(message)
        }
    }

    override fun showMessage(listMessage: MutableList<Message>) {to
        this.listMessage = listMessage
        messageAdapter = MessageAdapter(this.listMessage, from!!)

        binding.messageContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            scrollToPosition(listMessage.size -1)
            setHasFixedSize(true)
            adapter = messageAdapter
        }
    }

    override fun updateChat(message: Message) {
        lifecycleScope.launch {
            if((from == message.userReceiverId) && (message.userSenderId == to) ||
                (from == message.userSenderId) && (message.userReceiverId == to)) {
                listMessage.add(message)
                binding.messageContainer.scrollToPosition(listMessage.size - 1)
                messageAdapter.notifyItemInserted(listMessage.size -1)
            }
        }
    }

    override fun error(err: String) {
        toast(applicationContext, err)
    }
}