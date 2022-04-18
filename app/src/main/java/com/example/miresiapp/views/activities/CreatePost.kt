package com.example.miresiapp.views.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.createPost.*
import com.example.miresiapp.databinding.ActivityCreatePostBinding
import com.example.miresiapp.databinding.ActivityMainBinding
import com.example.miresiapp.models.City
import com.example.miresiapp.models.Post
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.utils.toast
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CreatePost : AppCompatActivity(), IPost.ViewPresenter, AdapterView.OnItemSelectedListener, View.OnClickListener {
    private lateinit var binding: ActivityCreatePostBinding
    private lateinit var listResi: MutableList<Residence>
    private lateinit var listCities: MutableList<City>
    private lateinit var listRooms: MutableList<Room>
    private lateinit var postDataProvider: PostDataProvider
    private lateinit var createPost: CreatePostImpl
    private var resiId: Int? = null
    private var roomId: Int? = null
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        postDataProvider = PostDataProvider()
        createPost = CreatePostImpl(this, postDataProvider, applicationContext)

        lifecycleScope.launch { createPost.requestCities() }
        binding.cities.onItemSelectedListener = this
        binding.residences.onItemSelectedListener = this
        binding.rooms.onItemSelectedListener = this
        binding.btnGoBack.setOnClickListener { onBackPressed() }
        binding.btnCreatePost.setOnClickListener(this)
    }

    override fun setDropDown(list: MutableList<Residence>?) {
        list?.let {
            listResi = it
            val dropDownResi: DropDownResi = DropDownResi(it)
            binding.residences.apply {
                adapter = dropDownResi
            }
        }
    }

    override fun setCitiesValues(list: MutableList<City>?) {
        list?.let {
            listCities = it
            val dropDownAdapter: DropDownAdapter = DropDownAdapter(it)
            binding.cities.apply {
                adapter = dropDownAdapter
            }
        }
    }

    override fun setRoomsValues(list: MutableList<Room>?) {
        list?.let {
            listRooms = it
            val dropDownRoom: DropDownRoom = DropDownRoom(it)
            binding.rooms.apply {
                adapter = dropDownRoom
            }
        }
    }

    override fun postCreated(valid: Boolean) {
        toast(applicationContext, valid)
    }

    override fun errorValidDate(err: String) {
        toast(applicationContext, err)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.cities -> {
                lifecycleScope.launch {
                    createPost.requestResi(listCities[position].name)
                }
            }
            R.id.residences ->{
                resiId = listResi[position].id
                lifecycleScope.launch {
                    createPost.requestRooms(listResi[position].id)
                }
            }
            R.id.rooms ->{
                roomId = listRooms[position].id
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onClick(v: View?) {
        val dateStart = binding.dateStart.text?.toString()
        val dateEnd = binding.dateEnd.text?.toString()

        if (dateStart?.isNotEmpty()!! || dateEnd?.isNotEmpty()!!){
            roomId?.let {
                lifecycleScope.launch {
                    createPost.createPost(resiId!!, it, dateStart, dateEnd!!)
                }
            }?: toast(applicationContext, "Error")
        } else toast(applicationContext, "Fill all fields")
    }

}