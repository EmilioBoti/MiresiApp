package com.example.miresiapp.businessLogic.comments

import com.example.miresiapp.models.CommentModel

interface IComment {

    interface ViewPresenter{
        fun showComments(listComments: MutableList<CommentModel>)
        fun setComment(listComments: CommentModel, size: Int?)
        fun error()
    }
    interface Presenter{
        suspend fun requestComments(resiId: Int, limit: Int)
        suspend fun pushComment(commentModel: CommentModel)
        suspend fun settingData(resiId: Int?, commentInput: String)
    }
    interface ModelPresenter{
        suspend fun getComments(resiId: Int, limit: Int): MutableList<CommentModel>?
        suspend fun postComment(comment: CommentModel): CommentModel?
    }
}