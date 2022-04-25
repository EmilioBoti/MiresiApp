package com.example.miresiapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.residence.DataProviderResi
import com.example.miresiapp.businessLogic.residence.IResi.PresenterView
import com.example.miresiapp.businessLogic.residence.ResiInteractorImpl
import com.example.miresiapp.businessLogic.residence.adapters.RoomAdapter
import com.example.miresiapp.databinding.ActivityResiInfoBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.utils.toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ResiInfoActivity : AppCompatActivity(), PresenterView, OnClickItemView {
    private lateinit var binding: ActivityResiInfoBinding
    private lateinit var gridLayout: GridLayout
    private var idResi: Int? = null
    private lateinit var model: DataProviderResi
    private lateinit var resiInteractorImpl: ResiInteractorImpl
    private var resi: MutableList<Residence>? = null
    private lateinit var listRooms: MutableList<Room>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        idResi = intent?.extras?.getInt("id")
        gridLayout = findViewById(R.id.facilitiesContainer)

        model = DataProviderResi()
        resiInteractorImpl = ResiInteractorImpl(this, model)

        idResi?.let {
            lifecycleScope.launch {
                resiInteractorImpl.getSingleResi(it)
                resiInteractorImpl.getRooms(it)
            }
        }

        binding.readMore.setOnClickListener {
            val inf = binding.about
            if (inf.maxLines == inf.lineCount){
                inf.maxLines = 3
                binding.readMore.text = resources.getString(R.string.readMore)
            } else{
                binding.readMore.text = resources.getString(R.string.readLess)
                inf.maxLines = inf.lineCount
            }
        }
    }
    
    override fun getResi(list: MutableList<Residence>?) {
        resi = list
        val residence: Residence? = resi?.get(0)
        Picasso.get().load(residence?.image).fit().centerCrop().into(binding.imageResi)
        binding.name.text = residence?.resiName
        binding.about.text = residence?.description
        facilities(residence)

    }

    override fun setRooms(list: MutableList<Room>) {
        listRooms = list
        val roomAdapter = RoomAdapter(listRooms, this)

        binding.roomContainer.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
            adapter = roomAdapter
        }
    }

    private fun facilities(residence: Residence?){
        if (residence?.gym != 0) LayoutInflater.from(applicationContext).inflate(R.layout.gym_layout_wtext, gridLayout)
        if (residence?.laundry != 0) LayoutInflater.from(applicationContext).inflate(R.layout.laundry_icon_wtext, gridLayout)
        if (residence?.parking_motorcycle != 0) LayoutInflater.from(applicationContext).inflate(R.layout.gym_layout_wtext, gridLayout)
        if (residence?.library != 0) LayoutInflater.from(applicationContext).inflate(R.layout.study_room_icon_wtext, gridLayout)
        if (residence?.parking_bicycle != 0) LayoutInflater.from(applicationContext).inflate(R.layout.bicycle_icon_wtext, gridLayout)
        if (residence?.parking_car != 0) LayoutInflater.from(applicationContext).inflate(R.layout.parking_icon_wtext, gridLayout)
    }

    override fun error(err: String) {

    }

    override fun onClickItem(pos: Int, view: View) {
    }

    override fun addFavoriteItem(pos: Int, view: View) {
    }

}