package com.example.miresiapp.businessLogic.forum.comments

import android.content.Context
import com.example.miresiapp.businessLogic.forum.ForumLogicImpl
import com.example.miresiapp.businessLogic.forum.ForumProvider
import com.example.miresiapp.businessLogic.forum.IForum
import com.example.miresiapp.models.forumModels.Comment

class ReplyCommPresenter(private val viewer: IComment.ViewPresenter, private val model: ForumProvider, context: Context)
    : ForumLogicImpl(viewer, model, context), IComment.Presenter {

    private  var listComments: MutableList<Comment>? = null

    override suspend fun requestComment(forumId: Int) {

    }

    override suspend fun requestReplyComments(fatherId: Int, forumId: Int) {
        listComments = model.getReplyComments(fatherId, forumId)
        listComments?.let {
            viewer.showReplyComments(it)
        }
    }
}