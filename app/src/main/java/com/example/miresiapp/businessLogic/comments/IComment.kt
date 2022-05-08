package com.example.miresiapp.businessLogic.comments

import com.example.miresiapp.models.CommentModel

interface IComment {

    interface ViewPresenter{
        fun setComments(listComments: MutableList<CommentModel>)
        fun error()
    }
    interface Presenter{
        suspend fun requestComments(resiId: Int, limit: Int)
        suspend fun pushComment(commentModel: CommentModel)
    }
    interface ModelPresenter{
        suspend fun getComments(resiId: Int, limit: Int): MutableList<CommentModel>?
    }
}