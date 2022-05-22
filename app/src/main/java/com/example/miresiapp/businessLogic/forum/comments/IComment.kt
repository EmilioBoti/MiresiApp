package com.example.miresiapp.businessLogic.forum.comments

import com.example.miresiapp.businessLogic.forum.IForum
import com.example.miresiapp.models.forumModels.Comment

interface IComment {
    interface ViewPresenter: IForum.ViewPresenter {
        fun showReplyComments(list: MutableList<Comment>)
    }
    interface Presenter {
        suspend fun requestComment(forumId: Int)
        suspend fun requestReplyComments(fatherId: Int, forumId: Int)
    }
}