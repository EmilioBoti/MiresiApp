package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.businessLogic.search.DataProviderSearch
import com.example.miresiapp.businessLogic.search.ISearch
import com.example.miresiapp.businessLogic.search.SearchLogicImpl
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.launch

class SearcherActivity : AppCompatActivity(), ISearch.ViewPresenter, OnClickItemView {
    private lateinit var recently: TextView
    private lateinit var btnGoBack: ImageView
    private lateinit var searcher: SearchView
    private lateinit var model: DataProviderSearch
    private lateinit var searchLogicImpl: SearchLogicImpl
    private lateinit var recyclerSuggest: RecyclerView
    private lateinit var listCities: MutableList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searcher)

        init()

        btnGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun init(){
        btnGoBack = findViewById(R.id.btnGoBack)
        recently = findViewById(R.id.text1)
        searcher = findViewById(R.id.searcher)
        recyclerSuggest = findViewById(R.id.suggestContainer)

        model = DataProviderSearch()
        searchLogicImpl = SearchLogicImpl(this, model)
    }

    override fun onStart() {
        super.onStart()

        searcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    newText?.let {
                        searchLogicImpl.requestSuggest(it.trim())
                    }
                }
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    query?.let {
                        searchLogicImpl.requestCity(it.trim())
                    }
                }
                return true
            }
        })
    }

    override fun citySearched(city: MutableList<City>) {
        navigateTo(city[0].name)
    }

    override fun suggestions(listCities: MutableList<City>) {
        this.listCities = listCities
        
        val cityAdapter: CityAdapter = CityAdapter(this.listCities, applicationContext, this)
        recyclerSuggest.apply {
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            adapter = cityAdapter
        }
    }

    override fun error(err: String) {
        toast(applicationContext, err)
    }

    override fun onClickItem(pos: Int) {
        lifecycleScope.launch {
            searchLogicImpl.requestCity(listCities[pos].name.trim())
        }
    }

    override fun addFavoriteItem(pos: Int, view: View) {}

    private fun navigateTo(city: String){
        finish()
        //supportParentActivityIntent
        Intent(this, DashBoardActivity::class.java).apply {
            putExtra("city", city)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(this)
        }
    }
}