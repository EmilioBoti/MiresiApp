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
import com.example.miresiapp.businessLogic.forum.IForum
import com.example.miresiapp.businessLogic.forum.adapters.CommentAdapter
import com.example.miresiapp.businessLogic.forum.comments.CommentPresenter
import com.example.miresiapp.databinding.FragmentCommentsBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.models.forumModels.Comment
import com.example.miresiapp.models.forumModels.ForumModel
import kotlinx.coroutines.launch

class CommentsFragment : Fragment(), IForum.ViewPresenter, OnClickItemView {
    private lateinit var binding: FragmentCommentsBinding
    private var forumId: Int? = null
    private lateinit var model: ForumProvider
    private lateinit var commentPresenter: CommentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCommentsBinding.bind(view)

        forumId = arguments?.getInt("forumId")
        model = ForumProvider()
        commentPresenter = CommentPresenter(this, model, requireActivity())

        forumId?.let {
            lifecycleScope.launch {
                commentPresenter.requestComment(it)
            }
        }


    }

    override fun showForumsComments(it: MutableList<Comment>) {
        val commentAdapter = CommentAdapter(it, this)
        binding.replyContainer.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = commentAdapter
        }
    }

    override fun showReplyComments(comment: Comment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.commentContainer, ReplyComment().apply {
                arguments = Bundle().apply {
                    putString("userName", comment.name)
                    putString("comment", comment.comments)
                    putString("image", comment.image)
                    putString("date", comment.date_created)
                    putInt("fatherId", comment.id)
                    putInt("forunmId", comment.forumId)
                }
            })
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onClickItem(pos: Int, view: View) {
        commentPresenter.getCommentClicked(pos)
    }

    override fun showForums(list: MutableList<ForumModel>) {
        TODO("Not yet implemented")
    }

    override fun setChips(list: MutableList<CategoryModel>) {
        TODO("Not yet implemented")
    }

    override fun setRooms(list: MutableList<Room>) {
        TODO("Not yet implemented")
    }

    override fun setCities(list: MutableList<City>) {
        TODO("Not yet implemented")
    }

    override fun setResis(list: MutableList<Residence>) {
        TODO("Not yet implemented")
    }

    override fun showForumReply(id: Int) {
        TODO("Not yet implemented")
    }

    override fun error(s: String) {
        TODO("Not yet implemented")
    }

    override fun addFavoriteItem(pos: Int, view: View) {
        TODO("Not yet implemented")
    }
}