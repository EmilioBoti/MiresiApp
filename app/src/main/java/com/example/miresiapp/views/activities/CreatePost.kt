package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.businessLogic.createPost.*
import com.example.miresiapp.databinding.ActivityCreatePostBinding
import com.example.miresiapp.models.City
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.launch

class CreatePost : AppCompatActivity(), IPost.ViewPresenter, View.OnClickListener {
    private lateinit var binding: ActivityCreatePostBinding
    private lateinit var listResi: MutableList<Residence>
    private lateinit var listCities: MutableList<City>
    private lateinit var listRooms: MutableList<Room>
    private lateinit var postDataProvider: PostDataProvider
    private lateinit var createPost: CreatePostImpl
    private var resiId: Int? = null
    private var roomId: Int? = null

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
        binding.btnGoBack.setOnClickListener { onBackPressed() }
        binding.btnCreatePost.setOnClickListener(this)

        binding.cities.setOnItemClickListener { parent, view, position, id ->
            lifecycleScope.launch {
                createPost.requestResi(listCities[position].name)
            }
        }
        binding.residences.setOnItemClickListener { parent, view, position, id ->
            resiId = listResi[position].id
            lifecycleScope.launch {
                createPost.requestRooms(listResi[position].id)
            }
        }
        binding.rooms.setOnItemClickListener { parent, view, position, id ->
            roomId = listRooms[position].id
        }
    }

    override fun setDropDown(list: MutableList<Residence>?) {
        val l = arrayListOf<String>()
        list?.let { it.forEach { l.add(it.resiName) } }
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,l)
        list?.let {
            listResi = it
            val dropDownResi: DropDownResi = DropDownResi(it)
            binding.residences.apply {
                setAdapter(adapter)
                //adapter = dropDownResi
            }
        }
    }

    override fun setCitiesValues(list: MutableList<City>?) {
        val l = arrayListOf<String>()
        list?.let { it.forEach { l.add(it.name) } }
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,l)
        list?.let {
            listCities = it
            val dropDownAdapter: DropDownAdapter = DropDownAdapter(it)
            binding.cities.apply {
                setAdapter(adapter)
            }
        }
    }

    override fun setRoomsValues(list: MutableList<Room>?) {
        val l = arrayListOf<String>()
        list?.let { it.forEach { l.add(it.name) } }
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,l)
        list?.let {
            listRooms = it
            val dropDownRoom: DropDownRoom = DropDownRoom(it)
            binding.rooms.setAdapter(adapter)
        }
    }

    override fun postCreated(valid: Boolean) {
        toast(applicationContext, valid)
    }

    override fun errorValidDate(err: String) {
        toast(applicationContext, err)
    }
    /* override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
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
            R.id.rooms ->{ roomId = listRooms[position].id }
        }
    }*/

   // override fun onNothingSelected(parent: AdapterView<*>?) {}

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