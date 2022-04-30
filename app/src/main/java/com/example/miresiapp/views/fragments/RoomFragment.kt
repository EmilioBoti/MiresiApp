package com.example.miresiapp.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.createPost.PostDataProvider
import com.example.miresiapp.businessLogic.posts.IPost
import com.example.miresiapp.businessLogic.posts.PostsLogicImpl
import com.example.miresiapp.businessLogic.posts.adapters.PostAdapter
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.PostModel
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.views.activities.ResiInfoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.socket.client.Socket
import kotlinx.coroutines.launch

class RoomFragment : Fragment(), View.OnClickListener, IPost.ViewPresenter, OnClickItemView {
    private lateinit var chatIcon: ImageView
    private lateinit var createPost: FloatingActionButton
    private var idUser: Int? = null
    private var fragmentLayout: FrameLayout? = null
    private var listPost: MutableList<PostModel> = mutableListOf()
    private lateinit var postContainer: RecyclerView
    private lateinit var postsLogicImpl: PostsLogicImpl
    private lateinit var postDataProvider: PostDataProvider
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatIcon = view.findViewById(R.id.chat)
        postContainer = view.findViewById(R.id.postContainer)

        fragmentLayout = activity?.findViewById<FrameLayout>(R.id.fragContainer)
        createPost = view.findViewById(R.id.createPoast)
        createPost.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()

        idUser = LocalData.getCurrentUserId(activity?.applicationContext!!)
        postDataProvider = PostDataProvider()
        postsLogicImpl = PostsLogicImpl(this, postDataProvider, activity)

        lifecycleScope.launch {
            postsLogicImpl.requestPost()
        }

        chatIcon.setOnClickListener {
            postsLogicImpl.navigaateTo()
        }
    }

    override fun onClick(v: View?) {
        postsLogicImpl.navCreatePost()
    }

    override fun showPost(list: MutableList<PostModel>) {
        listPost = list
        postAdapter = PostAdapter(listPost, idUser!!,this,)
        postContainer.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.VERTICAL, false)
            adapter = postAdapter
        }
    }

    override fun onClickItem(pos: Int, view: View) {
        when(view.id) {
            R.id.chatTo -> {
                postsLogicImpl.navToMessanger(listPost[pos].userId, listPost[pos].userName)
            }
            R.id.seeTo -> {
                navToRoom(pos)
            }
        }
    }

    override fun addFavoriteItem(pos: Int, view: View) {
        TODO("Not yet implemented")
    }

    private fun navToRoom(pos: Int) {
        //it not suppost to go to this screen, just for testing.
        Intent(activity?.applicationContext, ResiInfoActivity::class.java).apply {
            putExtra("id", listPost[pos].resiId)
            startActivity(this)
        }
    }
}