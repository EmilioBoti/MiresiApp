package com.example.miresiapp.views.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.forum.ForumLogicImpl
import com.example.miresiapp.businessLogic.forum.ForumProvider
import com.example.miresiapp.businessLogic.forum.IForum
import com.example.miresiapp.businessLogic.forum.adapters.ForumAdapter
import com.example.miresiapp.databinding.FragmentForumBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.ForumModel
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.launch

class ForumFragment : Fragment(), IForum.ViewPresenter, OnClickItemView {
    private lateinit var binding: FragmentForumBinding
    private lateinit var model: ForumProvider
    private lateinit var forumPresenter: IForum.Presenter
    private lateinit var forumAdapter: ForumAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForumBinding.bind(view)

        model = ForumProvider()
        forumPresenter = ForumLogicImpl(this, model)

        lifecycleScope.launch {
            forumPresenter.requestForums()
        }

        binding.createForum.setOnClickListener {
            toast(context, "Create Forum")
        }

        binding.city.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                toast(activity,buttonView.id)
            } else toast(activity,"not checked")
        }
    }

    override fun showForums(list: MutableList<ForumModel>) {

        forumAdapter = ForumAdapter(list, this)
        binding.forumContainer.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            adapter = forumAdapter
        }
    }

    override fun onClickItem(pos: Int, view: View) {
        toast(context, pos)
    }

    override fun addFavoriteItem(pos: Int, view: View) {
        TODO("Not yet implemented")
    }
}