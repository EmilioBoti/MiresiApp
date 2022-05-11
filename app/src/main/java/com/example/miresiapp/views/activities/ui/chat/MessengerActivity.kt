package com.example.miresiapp.views.activities.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.adapters.chatAdapter.MessageAdapter
import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.businessLogic.message.IMessage
import com.example.miresiapp.businessLogic.message.MessageLogicImpl
import com.example.miresiapp.databinding.ActivityMessengerBinding
import com.example.miresiapp.models.Message
import com.example.miresiapp.models.MessageModel
import com.example.miresiapp.utils.toast
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MessengerActivity : AppCompatActivity(), View.OnClickListener, IMessage.ViewPresenter {
    private lateinit var binding: ActivityMessengerBinding
    private var data: Bundle? = null
    private var from: Int? = null
    private var to: Int? = null
    private lateinit var message: MessageModel
    private lateinit var gson: Gson
    private lateinit var model: ChatDataProvider
    private lateinit var messengeLogicImpl: MessageLogicImpl
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
        messengeLogicImpl = MessageLogicImpl(this, model)
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
        if(binding.boxMessage.text.isNotEmpty()) {
            message = MessageModel(from!!, to!!, binding.boxMessage.text.toString().trim(), false)
            binding.boxMessage.setText("")
            messengeLogicImpl.sendMessage(message)
        }
    }

    override fun showMessage(listMessage: MutableList<Message>) {
        messageAdapter = MessageAdapter(listMessage, from!!)

        binding.messageContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            scrollToPosition(listMessage.size -1)
            setHasFixedSize(true)
            adapter = messageAdapter
        }
    }

    override fun updateChat(message: Message, pos: Int) {
        lifecycleScope.launch {
            if(screenUserValid(message)) {
                binding.messageContainer.scrollToPosition(pos)
                binding.messageContainer.setHasFixedSize(true)
                binding.messageContainer.fling(0, 500000)
                messageAdapter.notifyItemInserted(pos)
            }
        }
    }
    private fun screenUserValid(message: Message): Boolean {
        return (from == message.userReceiverId) && (message.userSenderId == to) || (from == message.userSenderId) && (message.userReceiverId == to)
    }
    override fun error(err: String) {
        toast(applicationContext, err)
    }
}