package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.businessLogic.city.CityBusinessLogic
import com.example.miresiapp.businessLogic.city.ICityInter
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.models.DataProvider

class MainBaseActivity : AppCompatActivity(), ICityInter.ViewPresenter, OnClickItemView, View.OnClickListener {
    private lateinit var listCities: MutableList<City>
    private lateinit var cityBusinessLogic: CityBusinessLogic
    private lateinit var model: DataProvider
    private lateinit var recyclerView: RecyclerView
    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_base)
    }

    override fun onStart() {
        super.onStart()

        listCities = mutableListOf()
        model = DataProvider()
        cityBusinessLogic = CityBusinessLogic(this,model)

        recyclerView = findViewById(R.id.containerCities)
        cityBusinessLogic.getListCities()
    }

    override fun setListCities(listCities: MutableList<City>) {
        Toast.makeText(this, "${listCities[0].name}",Toast.LENGTH_SHORT).show()
        this.listCities = listCities
        cityAdapter = CityAdapter(this.listCities, applicationContext!!, this)
        recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
            adapter = cityAdapter
        }
    }

    override fun onClickItem(pos: Int) {
        Intent(this, DashBoardActivity::class.java).apply {
            startActivity(this)
        }
        finish()
        Toast.makeText(applicationContext, listCities[pos].name, Toast.LENGTH_SHORT).show()
    }
    override fun onClick(v: View?) {
        Toast.makeText(applicationContext, "ok", Toast.LENGTH_SHORT).show()
    }


}