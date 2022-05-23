package com.example.miresiapp.views.fragments.forum

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.forum.CategoryModel
import com.example.miresiapp.businessLogic.forum.ForumLogicImpl
import com.example.miresiapp.businessLogic.forum.ForumProvider
import com.example.miresiapp.businessLogic.forum.IForum
import com.example.miresiapp.businessLogic.forum.adapters.ForumAdapter
import com.example.miresiapp.databinding.FragmentForumBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.models.forumModels.ForumModel
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.models.forumModels.Comment
import com.example.miresiapp.views.activities.ui.forums.CreateForumActivity
import com.example.miresiapp.views.activities.ui.forums.ForumReply
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class ForumFragment : Fragment(), IForum.ViewPresenter, OnClickItemView, CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: FragmentForumBinding
    private lateinit var model: ForumProvider
    private lateinit var forumPresenter: ForumLogicImpl
    private lateinit var forumAdapter: ForumAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForumBinding.bind(view)

        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation)!!
        bottomNavigationView.selectedItemId = R.id.pageForum
        model = ForumProvider()
        forumPresenter = ForumLogicImpl(this, model, activity?.applicationContext!!)

        lifecycleScope.launch {
            forumPresenter.requestForums()
            forumPresenter.requestCategories()
        }

        binding.createForum.setOnClickListener {
            Intent(activity, CreateForumActivity::class.java).apply {
                startActivity(this)
            }
        }

    }

    override fun showForums(list: MutableList<ForumModel>) {
        forumAdapter = ForumAdapter(list, this)
        binding.forumContainer.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = forumAdapter
        }
    }

    override fun setChips(list: MutableList<CategoryModel>) {
        for (i in 0 until list.size){
            binding.filter.addView(createChip(list[i].name))
        }
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
        activity?.let {
            Intent(it, ForumReply::class.java).apply {
                putExtra("forumId", id)
                startActivity(this)
            }
        }
    }

    override fun error(s: String) {
        TODO("Not yet implemented")
    }

    override fun showForumsComments(it: MutableList<Comment>) {
        TODO("Not yet implemented")
    }

    override fun showReplyComments(comment: Comment) {

    }

    private fun createChip(name: String): Chip{
        return Chip(activity).apply {
            this.text = name
            this.textSize = 18f
            this.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.black_200)))
            this.isCheckable = true
            this.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.white))
            this.checkedIconTint = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.red_pink_400))
            this.chipStrokeColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.green_turquoise_darker))
            this.chipStrokeWidth = 3f
            this.setOnCheckedChangeListener(this@ForumFragment)
        }
    }

    override fun onClickItem(pos: Int, view: View) {
        forumPresenter.clickedForum(pos)
    }

    override fun addFavoriteItem(pos: Int, view: View) {
        TODO("Not yet implemented")
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked){
            lifecycleScope.launch {
                forumPresenter.filterForums(buttonView?.text as String)
            }
        }else lifecycleScope.launch {
            forumPresenter.requestForums()
        }
    }
}