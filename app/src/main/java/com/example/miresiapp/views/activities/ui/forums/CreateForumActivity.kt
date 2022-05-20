package com.example.miresiapp.views.activities.ui.forums

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.forum.CategoryModel
import com.example.miresiapp.businessLogic.forum.ForumLogicImpl
import com.example.miresiapp.businessLogic.forum.ForumProvider
import com.example.miresiapp.businessLogic.forum.IForum
import com.example.miresiapp.databinding.ActivityCreateForumBinding
import com.example.miresiapp.models.City
import com.example.miresiapp.models.ForumModel
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.utils.toast
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class CreateForumActivity : AppCompatActivity(), IForum.ViewPresenter,
    CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    private lateinit var binding: ActivityCreateForumBinding
    private lateinit var forumModel: ForumProvider
    private lateinit var forumLogicImpl: ForumLogicImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        forumModel = ForumProvider()
        forumLogicImpl = ForumLogicImpl(this, forumModel)

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

        }

    }

    override fun showForums(list: MutableList<ForumModel>) {
        //not implement
    }

    override fun setChips(list: MutableList<CategoryModel>) {
        list.forEach {
            binding.tagContainer.addView(createChip(it.name))
        }
    }

    override fun setRooms(list: MutableList<Room>) {

    }

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

    private fun createChip(name: String): Chip {
        return Chip(this).apply {
            this.text = name
            this.textSize = 18f
            this.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.black_200)))
            this.isCheckable = true
            this.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.white))
            this.checkedIconTint = ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.red_pink_400))
            this.chipStrokeColor = ColorStateList.valueOf(ContextCompat.getColor(this@CreateForumActivity,R.color.red_pink_400))
            this.chipStrokeWidth = 3f
            this.setOnCheckedChangeListener(this@CreateForumActivity)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

    }

    override fun onClick(v: View?) {
        toast(this, "ok")
    }
}