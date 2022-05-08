package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.comments.CommentLogicImpl
import com.example.miresiapp.businessLogic.comments.CommentProvider
import com.example.miresiapp.businessLogic.comments.IComment.ViewPresenter
import com.example.miresiapp.businessLogic.comments.adapters.CommentAdapter2
import com.example.miresiapp.businessLogic.residence.adapters.CommentAdapter
import com.example.miresiapp.databinding.ActivityCommentBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.launch

class CommentActivity : AppCompatActivity(), ViewPresenter, OnClickItemView {
    private lateinit var binding: ActivityCommentBinding
    private var resiId: Int? = null
    private lateinit var model: CommentProvider
    private lateinit var commentPresenter: CommentLogicImpl
    private lateinit var commentAdapter: CommentAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        resiId = intent?.getIntExtra("resiId", 0)
        model = CommentProvider()
        commentPresenter = CommentLogicImpl(this, model)


        resiId?.let {
            if (it > 0){
                lifecycleScope.launch {
                    commentPresenter.requestComments(it, 20)
                }
            }
        }

        binding.btnSender.setOnClickListener {
            toast(applicationContext, "ok")
        }
    }

    override fun setComments(listComments: MutableList<CommentModel>) {
        commentAdapter = CommentAdapter2(listComments, this)

        binding.commentContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            adapter = commentAdapter
        }
    }

    override fun error() {
        TODO("Not yet implemented")
    }

    override fun onClickItem(pos: Int, view: View) {
        TODO("Not yet implemented")
    }

    override fun addFavoriteItem(pos: Int, view: View) {
        TODO("Not yet implemented")
    }


}