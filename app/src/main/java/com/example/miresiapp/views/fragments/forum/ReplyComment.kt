package com.example.miresiapp.views.fragments.forum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.forum.CategoryModel
import com.example.miresiapp.businessLogic.forum.ForumProvider
import com.example.miresiapp.businessLogic.forum.adapters.CommentAdapter
import com.example.miresiapp.businessLogic.forum.comments.IComment
import com.example.miresiapp.businessLogic.forum.comments.ReplyCommPresenter
import com.example.miresiapp.databinding.FragmentReplyCommentBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.models.forumModels.Comment
import com.example.miresiapp.models.forumModels.ForumModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ReplyComment : Fragment(), IComment.ViewPresenter, OnClickItemView {
    private lateinit var binding: FragmentReplyCommentBinding
    private var data: Bundle? = null
    private lateinit var model: ForumProvider
    private lateinit var commentPresenter: ReplyCommPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reply_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReplyCommentBinding.bind(view)
        data = arguments

        model = ForumProvider()
        commentPresenter = ReplyCommPresenter(this, model, activity?.applicationContext!!)

        data?.let {
            binding.userName.text = it.getString("userName")
            binding.comment.text = it.getString("comment")
            binding.date.text = it.getString("date")
            Picasso.get().load(it.getString("image")).into(binding.userImage)

            lifecycleScope.launch {
                commentPresenter.requestReplyComments(it.getInt("fatherId"), it.getInt("forunmId"))
            }
        }
    }

    override fun showReplyComments(list: MutableList<Comment>) {
        val replyAdapter = CommentAdapter(list, this)
        binding.replyContainer.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = replyAdapter
        }
    }

    override fun onClickItem(pos: Int, view: View) {
    }

    override fun addFavoriteItem(pos: Int, view: View) {
    }

    override fun showReplyComments(comment: Comment) {
    }

    override fun showForums(list: MutableList<ForumModel>) {
    }

    override fun setChips(list: MutableList<CategoryModel>) {
    }

    override fun setRooms(list: MutableList<Room>) {
    }

    override fun setCities(list: MutableList<City>) {
    }

    override fun setResis(list: MutableList<Residence>) {
    }

    override fun showForumReply(id: Int) {
    }

    override fun error(s: String) {
    }

    override fun showForumsComments(it: MutableList<Comment>) {

    }
}