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
import com.example.miresiapp.models.Message
import com.example.miresiapp.models.MessageModel
import com.example.miresiapp.utils.toast
import com.google.gson.Gson
import io.socket.client.Socket
import kotlinx.coroutines.launch

class MessengerActivity : AppCompatActivity(), View.OnClickListener, IMessenger.ViewPresenter {
    private lateinit var mSocket: Socket
    private lateinit var btnSender: ImageButton
    private lateinit var boxInput: EditText
    private lateinit var messageContainer: RecyclerView
    private lateinit var btnGoBack: ImageView
    private lateinit var userName: TextView
    private lateinit var listMessage: MutableList<Message>
    private var data: Bundle? = null
    private var userSenderId: Int? = null
    private var userReceiverId: Int? = null
    private lateinit var userSenderSOId: String
    private lateinit var userReceiverSOId: String
    private lateinit var message: MessageModel
    private lateinit var gson: Gson
    private lateinit var model: ChatDataProvider
    private lateinit var messengeLogicImpl: MessengeLogicImpl
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
        data = intent?.extras

    }

    override fun onStart() {
        super.onStart()

        gson = Gson()
        mSocket = SocketCon.getSocket()
        messageContainer = findViewById(R.id.messageContainer)
        userName = findViewById(R.id.userNameSelected)
        boxInput = findViewById(R.id.boxMessage)
        btnGoBack = findViewById(R.id.btnGoBack)
        btnSender = findViewById(R.id.btnSender)
        btnSender.setOnClickListener(this)
        model = ChatDataProvider()
        listMessage = mutableListOf()
        messengeLogicImpl = MessengeLogicImpl(this, model)
        messageAdapter = MessageAdapter(listMessage)
        userSenderId = data?.getInt("senderUserId")
        userReceiverId = data?.getInt("receiverUserId")
        userReceiverSOId = data?.getString("receiverSOId", "")!!
        userName.text = data?.getString("name", "")!!


        requestSMS(userSenderId!!, userReceiverId!!)

        lifecycleScope.launch {
            messengeLogicImpl.requestMessage(userSenderId!!, userReceiverId!!)
        }
        btnGoBack.setOnClickListener {
            onBackPressed()
        }
    }
    private fun requestSMS(from: Int, to: Int){
        if (mSocket.connected()){
            userSenderSOId = mSocket.id()
        }
    }
    override fun onClick(v: View?) {
        message = MessageModel(userSenderId!!, userReceiverId!!, boxInput.text.toString(), false)
        val msm: String = gson.toJson(message)
        boxInput.setText("")
        mSocket.emit("message", msm)
    }

    override fun showMessage(listMessage: MutableList<Message>) {
        this.listMessage = listMessage
        messageAdapter = MessageAdapter(this.listMessage)

        messageContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            scrollToPosition(listMessage.size - 1)
            adapter = messageAdapter
        }
    }

    override fun updateChat(message: Message) {
        lifecycleScope.launch {
            if((userSenderId == message.userReceiverId) && (message.userSenderId == userReceiverId) ||
                (userSenderId == message.userSenderId) && (message.userReceiverId == userReceiverId)){
                listMessage.add(message)
                messageContainer.scrollToPosition(listMessage.size - 1)
                messageAdapter.notifyItemInserted(listMessage.size -1)
            }
        }
    }

    override fun error(err: String) {
        toast(applicationContext, err)
    }
}