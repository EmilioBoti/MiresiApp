package com.example.miresiapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.businessLogic.city.CityBusinessLogic
import com.example.miresiapp.businessLogic.city.ICityInter
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.models.DataProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class CityFragment : Fragment(), ICityInter.ViewPresenter, OnClickItemView, View.OnClickListener {
    private lateinit var listCities: MutableList<City>
    private lateinit var cityBusinessLogic: CityBusinessLogic
    private lateinit var model: DataProvider
    private lateinit var recyclerView: RecyclerView
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listCities = mutableListOf()
        model = DataProvider()
        cityBusinessLogic = CityBusinessLogic(this,model)

        recyclerView = view.findViewById(R.id.containerCities)

        lifecycleScope.launch {
            cityBusinessLogic.getListCities()
        }
    }
    override fun setListCities(listCities: MutableList<City>) {
        this.listCities = listCities
        cityAdapter = CityAdapter(listCities, activity?.applicationContext!!, this)
        recyclerView.apply {
            layoutManager = GridLayoutManager(activity?.applicationContext, 2, GridLayoutManager.VERTICAL, false)
            adapter = cityAdapter
        }
    }

    override fun onClickItem(pos: Int) {
        Toast.makeText(activity?.applicationContext, listCities[pos].name, Toast.LENGTH_SHORT).show()
    }
    override fun onClick(v: View?) {
        Toast.makeText(activity?.applicationContext, "ok", Toast.LENGTH_SHORT).show()
    }

}