package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.createPost.CreatePostImpl
import com.example.miresiapp.businessLogic.createPost.DropDownAdapter
import com.example.miresiapp.businessLogic.createPost.IPost
import com.example.miresiapp.businessLogic.createPost.PostDataProvider
import com.example.miresiapp.databinding.ActivityCreatePostBinding
import com.example.miresiapp.databinding.ActivityMainBinding
import com.example.miresiapp.models.Residence
import kotlinx.coroutines.launch

class CreatePost : AppCompatActivity(), IPost.ViewPresenter {
    private lateinit var binding: ActivityCreatePostBinding
    private lateinit var listResi: MutableList<Residence>
    private lateinit var postDataProvider: PostDataProvider
    private lateinit var createPost: CreatePostImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        postDataProvider = PostDataProvider()
        createPost = CreatePostImpl(this, postDataProvider)

        lifecycleScope.launch { createPost.requestResi("barcelona") }

        binding.btnGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun setDropDown(list: MutableList<Residence>?) {
        list?.let {
            listResi = it
            val dropDownAdapter: DropDownAdapter = DropDownAdapter(it)
            binding.residences.apply {
                adapter = dropDownAdapter
            }
        }

    }
}