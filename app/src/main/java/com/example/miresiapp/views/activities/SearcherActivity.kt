package com.example.miresiapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.businessLogic.search.DataProviderSearch
import com.example.miresiapp.businessLogic.search.ISearch
import com.example.miresiapp.businessLogic.search.SearchLogicImpl
import com.example.miresiapp.databinding.ActivitySearcherBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.launch


//not in use
class SearcherActivity : AppCompatActivity(), ISearch.ViewPresenter, OnClickItemView {
    private lateinit var binding: ActivitySearcherBinding
    private lateinit var model: DataProviderSearch
    private lateinit var searchLogicImpl: SearchLogicImpl
    private lateinit var listCities: MutableList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearcherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()

        model = DataProviderSearch()
        searchLogicImpl = SearchLogicImpl(this, model)

        binding.searcher.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch {
                searchLogicImpl.requestSuggest(text.toString().trim())
            }
        }
        binding.searcher.setOnItemClickListener { parent, view, position, id ->
            lifecycleScope.launch {
                searchLogicImpl.requestCity(listCities[position].name.trim())
            }
        }
    }

    override fun citySearched(city: MutableList<City>) {
        navigateTo(city[0].name)
    }

    override fun suggestions(listCities: MutableList<City>) {
        this.listCities = listCities
        val l = arrayListOf<String>()
        this.listCities.let { it.forEach { l.add(it.name) } }

        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item,l)
        binding.searcher.setAdapter(adapter)
    }

    override fun error(err: String) {
        toast(applicationContext, err)
    }

    override fun onClickItem(pos: Int, view: View) {
        lifecycleScope.launch {
            searchLogicImpl.requestCity(listCities[pos].name.trim())
        }
    }
    
    override fun addFavoriteItem(pos: Int, view: View) {}

    private fun navigateTo(city: String){
        finish()
        Intent(this, DashBoardActivity::class.java).apply {
            putExtra("city", city)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(this)
        }
    }
}