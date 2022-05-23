package com.example.miresiapp.views.activities.ui.forums

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.createPost.DropDownAdapter
import com.example.miresiapp.businessLogic.forum.*
import com.example.miresiapp.databinding.ActivityCreateForumBinding
import com.example.miresiapp.models.*
import com.example.miresiapp.models.forumModels.Comment
import com.example.miresiapp.models.forumModels.ForumModel
import com.example.miresiapp.utils.toast
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class CreateForumActivity : AppCompatActivity(), IForum.ViewPresenter,
    CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private lateinit var binding: ActivityCreateForumBinding
    private lateinit var forumModel: ForumProvider
    private lateinit var forumLogicImpl: ForumLogicImpl
    private var resiId: Int = 0
    private var cityId: Int = 0
    private var categId: Int = 0
    private lateinit var about: String
    private val iconsList = arrayListOf<IconModel>(
        IconModel("Talk", R.drawable.forum_24),
        IconModel("Beach", R.drawable.beach_access_24),
        IconModel("Pool", R.drawable.pool_24)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        forumModel = ForumProvider()
        forumLogicImpl = ForumLogicImpl(this, forumModel, this)

        binding.btnCreateForum.setOnClickListener(this)

        lifecycleScope.launch {
            forumLogicImpl.requestCategories()
        }
        lifecycleScope.launch {
            forumLogicImpl.requestCities()
        }

        binding.btnGoBack.setOnClickListener {
            onBackPressed()
        }

        binding.cities.setOnItemClickListener { parent, view, position, id ->
            val name = view as TextView
            lifecycleScope.launch {
                forumLogicImpl.getResis( position ,name.text.toString().trim())
            }
        }
        binding.resiDrop.setOnItemClickListener { parent, view, position, id ->
            forumLogicImpl.getResiId(position)
        }

        setIcons()
    }

    private fun setIcons() {
         val adapter = DropDownAdapter(iconsList)
        //val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, l)
        binding.iconDrop.adapter = adapter
        binding.iconDrop.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //toast(this@CreateForumActivity, iconsList[position].icon)
                this@CreateForumActivity.forumLogicImpl.setIconId(this@CreateForumActivity.iconsList[position].icon)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    override fun showForums(list: MutableList<ForumModel>) { /*not implement */ }

    override fun setChips(list: MutableList<CategoryModel>) {
        binding.tagContainer.removeAllViews()
        list.forEach {
            binding.tagContainer.addView(createChip(it.id, it.name))
        }
    }

    override fun setRooms(list: MutableList<Room>) { }

    override fun setCities(list: MutableList<City>) {
        val l = arrayListOf<String>()
        list.forEach { l.add(it.name) }
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,l)
        binding.cities.apply {
            this.setAdapter(adapter)
        }
    }

    override fun setResis(list: MutableList<Residence>) {
        val l = arrayListOf<String>()
        list.forEach { l.add(it.resiName) }
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,l)
        binding.resiDrop .apply {
            this.setAdapter(adapter)
        }
    }

    override fun showForumReply(id: Int) {
        TODO("Not yet implemented")
    }

    override fun error(s: String) {
        toast(this, s)
    }

    override fun showForumsComments(it: MutableList<Comment>) {
        TODO("Not yet implemented")
    }

    override fun showReplyComments(comment: Comment) {
        TODO("Not yet implemented")
    }

    private fun createChip(id: Int, name: String): Chip {
        return Chip(this).apply {
            this.id = id
            this.text = name
            this.textSize = 18f
            this.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.black_200)))
            this.isCheckable = true
            this.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.white))
            this.checkedIconTint = ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.red_pink_400))
            this.chipStrokeColor = ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.green_turquoise_darker))
            this.chipStrokeWidth = 3f
            this.setOnCheckedChangeListener(this@CreateForumActivity)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            val cateId: Int? = buttonView?.id
            forumLogicImpl.setCateId(cateId)
        }
    }

    override fun onClick(v: View?) {
        val name = binding.inputName.text.toString()
        if (name.isNotEmpty()) {
            lifecycleScope.launch {
                forumLogicImpl.publish(name)
            }
        } else toast(this, "fill all.")
    }
}