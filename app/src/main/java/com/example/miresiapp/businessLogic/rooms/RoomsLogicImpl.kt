package com.example.miresiapp.businessLogic.rooms

import android.content.Context
import android.content.Intent
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.createPost.PostDataProvider
import com.example.miresiapp.models.PostModel
import com.example.miresiapp.views.activities.ChatActivity
import com.example.miresiapp.views.activities.CreatePost
import com.example.miresiapp.views.activities.MessengerActivity

class RoomsLogicImpl(private val viewer: IRooms.ViewPresenter,private val model: PostDataProvider, private val context: Context?): IRooms.Presenter {
    private var idUser: Int? = null

    init {
        idUser = getCurrentUserData()
    }

    override suspend fun requestPost() {
        val list: MutableList<PostModel>? = model.getPost()
        viewer.showPost(list!!)
    }

    override fun navigaateTo() {
        idUser?.let {
            Intent(context, ChatActivity::class.java).apply {
                this.putExtra("id", it)
                context?.startActivity(this)
            }
        }
    }

    override fun navCreatePost() {
        idUser?.let {
            Intent(context, CreatePost::class.java).apply {
                context?.startActivity(this)
            }
        }
    }

    override fun navToMessanger(idTo: Int, userName: String) {
        idUser?.let {
            Intent(context?.applicationContext, MessengerActivity::class.java).apply {
                putExtra("name", userName)
                putExtra("from", idUser)
                putExtra("to", idTo)
                context?.startActivity(this)
            }
        }
    }

    private fun getCurrentUserData(): Int? {
        val prefe = context?.getSharedPreferences(context.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
        return prefe?.getInt("userId", 0)
    }
}
