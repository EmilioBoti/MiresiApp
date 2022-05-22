package com.example.miresiapp.businessLogic.forum.comments

import android.content.Context
import com.example.miresiapp.businessLogic.forum.ForumLogicImpl
import com.example.miresiapp.businessLogic.forum.ForumProvider
import com.example.miresiapp.businessLogic.forum.IForum
import com.example.miresiapp.models.forumModels.Comment

class CommentPresenter(private val viewer: IForum.ViewPresenter, private val model: ForumProvider, context: Context)
    : ForumLogicImpl(viewer, model, context), IComment.Presenter {

    private  var listComments: MutableList<Comment>? = null

    override suspend fun requestComment(forumId: Int) {
        listComments = model.getCommentForum(forumId)
        listComments?.let {
            viewer.showForumsComments(it)
        }
    }

    override suspend fun requestReplyComments(fatherId: Int, forumId: Int) {
        val list = model.getReplyComments(fatherId, forumId)
        list?.let {

        }
    }

    fun getCommentClicked(pos: Int) {
        listComments?.let {
            val commentId = it[pos].id
            val forumId = it[pos].forumId
            viewer.showReplyComments(commentId, forumId)
        }
    }

}