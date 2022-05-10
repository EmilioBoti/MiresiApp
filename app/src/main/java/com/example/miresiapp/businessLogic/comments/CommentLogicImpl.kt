package com.example.miresiapp.businessLogic.comments

import android.content.Context
import android.util.Log
import com.example.miresiapp.SocketCon
import com.example.miresiapp.businessLogic.comments.IComment.*
import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.toast
import com.google.gson.Gson
import io.socket.client.Socket
import java.text.SimpleDateFormat
import java.util.*

class CommentLogicImpl(private val view: ViewPresenter, private val model: ModelPresenter, private val context: Context): Presenter {
    private var listComment: MutableList<CommentModel>? = null
    private var userId: Int? = null
    private var userImage: String? = null
    private var userName: String? = null
    private val mSocket: Socket = SocketCon.getSocket()
    private val gson: Gson = Gson()

    init {
        userId = LocalData.getCurrentUserId(context)
        userImage = LocalData.getImageUser(context)
        userName = LocalData.getCurrentUserName(context)
    }

    override suspend fun requestComments(resiId: Int, limit: Int) {
        listComment = model.getComments(resiId, limit)
        listComment?.let {
            view.showComments(it)
        }
        /*mSocket.on("commentPosted"){ data ->
            val comment: CommentModel = gson.fromJson(data[0].toString(), CommentModel::class.java)
            listComment?.add(comment)
            view.setComment(comment, listComment?.size)
        }*/
    }

    override suspend fun pushComment(commentModel: CommentModel) {
        //mSocket.emit("postComment", gson.toJson(commentModel))
        val comment: CommentModel? = model.postComment(commentModel)
        comment?.let {
            listComment?.add(it)
            view.setComment(it, listComment?.size)
        }
    }

    override suspend fun settingData(resiId: Int?, commentInput: String) {
        val date = currentDate()
        val comment = CommentModel(userId!!, userName, resiId!!, commentInput, date, userImage)
        pushComment(comment)
    }

    private fun currentDate(): String {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss a")
        return date.format(Date().time)
    }

}