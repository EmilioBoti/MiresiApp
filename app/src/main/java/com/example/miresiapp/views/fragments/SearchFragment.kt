package com.example.miresiapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.home.IHome
import com.example.miresiapp.businessLogic.search.DataProviderSearch
import com.example.miresiapp.businessLogic.search.ISearch
import com.example.miresiapp.businessLogic.search.SearchLogicImpl
import com.example.miresiapp.databinding.FragmentSearchBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.launch

class SearchFragment(private val navigateTo: IHome.ViewPresenter ) : Fragment(), ISearch.ViewPresenter, OnClickItemView {
    private var binding: FragmentSearchBinding? = null
    private lateinit var model: DataProviderSearch
    private lateinit var searchLogicImpl: SearchLogicImpl
    private lateinit var listCities: MutableList<City>
    private lateinit var residencesFragment: ResidenceFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)


        model = DataProviderSearch()
        searchLogicImpl = SearchLogicImpl(this, model)

        binding?.searcher?.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch {
                searchLogicImpl.requestSuggest(text.toString().trim())
            }
        }
        binding?.searcher?.setOnItemClickListener { parent, view, position, id ->
            lifecycleScope.launch {
                searchLogicImpl.requestCity(listCities[position].name.trim())
            }
        }
        binding?.btnGoBack?.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun citySearched(city: MutableList<City>) {
        //navigateTo.navigateTo(city[0].name)
        activity?.supportFragmentManager?.popBackStack()
        val data = Bundle().apply { putString("city", city[0].name) }

        residencesFragment = ResidenceFragment().apply { arguments = data }
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.viewContainer, residencesFragment)
            ?.addToBackStack("search")
            ?.commit()
    }

    override fun suggestions(listCities: MutableList<City>) {
        this.listCities = listCities
        val l = arrayListOf<String>()
        this.listCities.let { it.forEach { l.add(it.name) } }

        val adapter = ArrayAdapter(activity?.applicationContext!!, android.R.layout.simple_spinner_dropdown_item,l)
        binding?.searcher?.setAdapter(adapter)
    }

    override fun error(err: String) {
       toast(activity, err)
    }

    override fun onClickItem(pos: Int, view: View) {
        lifecycleScope.launch {
            searchLogicImpl.requestCity(listCities[pos].name.trim())
        }
    }

    override fun addFavoriteItem(pos: Int, view: View) {}
}