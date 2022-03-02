package com.example.miresiapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.businessLogic.city.CityBusinessLogic
import com.example.miresiapp.businessLogic.city.ICityInter
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.models.DataProvider
import kotlinx.coroutines.launch

class CityActivity : AppCompatActivity(), ICityInter.ViewPresenter, OnClickItemView {
    private lateinit var listCities: MutableList<City>
    private lateinit var cityBusinessLogic: CityBusinessLogic
    private lateinit var model: DataProvider
    private lateinit var recyclerView: RecyclerView
    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

    }

    override fun onStart() {
        super.onStart()
        listCities = mutableListOf()
        model = DataProvider()
        cityBusinessLogic = CityBusinessLogic(this,model)

        recyclerView = findViewById(R.id.containerCities)
        lifecycleScope.launch {
            cityBusinessLogic.getListCities()
        }
    }
    override fun setListCities(listCities: MutableList<City>) {
        this.listCities = listCities
        cityAdapter = CityAdapter(listCities, this, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = cityAdapter
        }
    }

    override fun onClickItem(pos: Int) {
        Toast.makeText(applicationContext, listCities[pos].name, Toast.LENGTH_SHORT).show()
    }
}