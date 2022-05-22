package com.example.miresiapp.businessLogic.forum

import com.example.miresiapp.businessLogic.forum.comments.IComment
import com.example.miresiapp.models.*
import com.example.miresiapp.models.forumModels.Comment
import com.example.miresiapp.models.forumModels.Forum
import com.example.miresiapp.models.forumModels.ForumComment
import com.example.miresiapp.models.forumModels.ForumModel

interface IForum {
    interface ViewPresenter: IComment{
        fun showForums(list: MutableList<ForumModel>)
        fun setChips(list: MutableList<CategoryModel>)
        fun setRooms(list: MutableList<Room>)
        fun setCities(list: MutableList<City>)
        fun setResis(list: MutableList<Residence>)
        fun showForumReply(id: Int)
        fun error(s: String)
        fun showForumsComments(it: MutableList<Comment>)
        fun showReplyComments(commentId: Int, forumId: Int)
    }
    interface Presenter{
        suspend fun requestForums()
        suspend fun requestCategories()
        suspend fun filterForums(name: String)
        suspend fun getResis(position: Int, city: String)
        suspend fun requestCities()
        suspend fun publish(name: String)
    }
    interface ModelPresenter{
        suspend fun getForums(): MutableList<ForumModel>?
        suspend fun getFilterForums(name: String): MutableList<ForumModel>?
        suspend fun getCategories(): MutableList<CategoryModel>?
        suspend fun publishForum(forum: Forum): Boolean?
        suspend fun getCommentForum(forumId: Int): MutableList<Comment>?
        suspend fun getReplyComments(fatherId: Int, forumId: Int): MutableList<Comment>?

    }
}